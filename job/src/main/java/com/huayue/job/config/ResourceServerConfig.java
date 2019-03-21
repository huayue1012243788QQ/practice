package com.huayue.job.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/3/15.
 * @description
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/webjars/**","/resources/**","/swagger-resources/**","/swagger-ui.html#/","/swagger-ui.html#","/api-doc","/v2/api-docs").permitAll()
                .anyRequest().authenticated();
    }

}
