package com.zoxal.queuebrain.config;

import com.zoxal.queuebrain.controller.UsersController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Insert description vere
 *
 * @author Mike
 * @version 12/11/2017
 */
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    private static final Logger logger = LoggerFactory.getLogger(UsersController.class);

    protected Class<?>[] getRootConfigClasses() {
        logger.debug("AppInitializer: getting root config classes");
        return new Class[]{AppConfig.class};
    }

    protected Class<?>[] getServletConfigClasses() {
        return null;
    }

    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
