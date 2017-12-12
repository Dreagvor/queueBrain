package com.zoxal.queuebrain.config;

import com.zoxal.queuebrain.controller.UsersController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Insert description vere
 *
 * @author Mike
 * @version 12/12/2017
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.zoxal.queuebrain.controller"})
public class AppConfig {

}
