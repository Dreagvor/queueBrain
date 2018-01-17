package com.zoxal.queuebrain.config;

import com.zoxal.queuebrain.api.rest.UsersController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Spring app initializer class. Picked up by spring's servlet
 * context initializer at application startup and loads configuration
 * classes.
 *
 * @author Mike
 * @version 12/11/2017
 */
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    private static final Logger logger = LoggerFactory.getLogger(UsersController.class);

    protected Class<?>[] getRootConfigClasses() {
        logger.debug("AppInitializer: getting root config classes");
//        return new Class[]{AppConfig.class};
//        return new Class[]{AppConfig.class, DataConfig.class};
        return new Class[]{AppConfig.class, DataConfig.class, SecurityConfig.class};
    }

    protected Class<?>[] getServletConfigClasses() {
        return null;
    }

    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
