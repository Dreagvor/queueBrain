package com.zoxal.queuebrain.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Application configuration class.
 *
 * @author Mike
 * @version 12/12/2017
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {
        "com.zoxal.queuebrain.api",
        "com.zoxal.queuebrain.repository",
        "com.zoxal.queuebrain.security.api",
        "com.zoxal.queuebrain.exceptions"})
public class AppConfig {
//    private static final Logger logger = LoggerFactory.getLogger(AppConfig.class);

    public AppConfig() {
//        logger.debug("Instantiating app config");
    }

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
//        logger.debug("Creating objectMapper");
        ObjectMapper mapper = new ObjectMapper();
        // TODO: hmm doesn't work
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
        return mapper;
    }

    @Bean
    public SerializationConfig serializationConfig(ObjectMapper objectMapper) {
        return objectMapper.getSerializationConfig();
    }
}
