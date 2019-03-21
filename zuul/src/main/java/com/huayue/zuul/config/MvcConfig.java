package com.huayue.zuul.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/3/19.
 * @description
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/api-doc").setViewName("redirect:/swagger-ui.html");
    }
}
