package com.zoxal.queuebrain.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zoxal.queuebrain.repository.UserRepository;
import com.zoxal.queuebrain.security.handlers.QueueBrainAuthenticationEntryPoint;
import com.zoxal.queuebrain.security.service.EmailPassAuthProvider;
import com.zoxal.queuebrain.security.service.ReglinkAuthProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.savedrequest.NullRequestCache;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Performs security configuration.
 *
 * @author Mike
 * @version 01/06/2018
 */
@EnableWebSecurity
@PropertySource(value = "classpath:email-credentials.properties")
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    private final Environment env;

    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public SecurityConfig(Environment env, UserRepository userRepository, ObjectMapper objectMapper) {
        this.env = env;
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        logger.debug("Setting up http");
//        http.authorizeRequests().antMatchers("/**").permitAll();
        http.csrf().disable();
        http.cors();
        http.requestCache().requestCache(new NullRequestCache());
        http.authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/login*", "/registration*").permitAll()
                .antMatchers(HttpMethod.GET, "/queues", "/users").permitAll()
                .anyRequest().authenticated();
        http.exceptionHandling().authenticationEntryPoint(new QueueBrainAuthenticationEntryPoint(objectMapper));
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public SecurityContextRepository securityContextRepository() {
        return new HttpSessionSecurityContextRepository();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(new EmailPassAuthProvider(userRepository))
                .authenticationProvider(new ReglinkAuthProvider(userRepository));
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("http://qbrain.of.by", "https://qbrain.of.by"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200", "http://localhost", "http://qbrain.of.by", "http://qbrain.of.by:80"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public JavaMailSender mailSender() {
        logger.debug("Setting up mail sender");
        JavaMailSenderImpl sender = new JavaMailSenderImpl();

        Properties javaMailProperites = new Properties();
        javaMailProperites.put("mail.smtp.auth", env.getProperty("mail.smtp.auth"));
        javaMailProperites.put("mail.smtp.starttls.enable", env.getProperty("mail.smtp.starttls.enable"));
        javaMailProperites.put("mail.smtp.host", env.getProperty("mail.smtp.host"));
        javaMailProperites.put("mail.smtp.port", env.getProperty("mail.smtp.port"));

        sender.setUsername(env.getProperty("mail.user"));
        sender.setPassword(env.getProperty("mail.password"));
        sender.setJavaMailProperties(javaMailProperites);
        return sender;
    }
}
