package com.huayue.zuul;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/3/19.
 * @description
 */
@EnableSwagger2
@Configuration
@Primary
public class SwaggerConfig implements SwaggerResourcesProvider {
    @Autowired
    RouteLocator routeLocator;
    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();
        resources.add(swaggerResource("job-service","/api/company/v2/api-docs","1.0"));
        resources.add(swaggerResource("user-service","/api/user/v2/api-docs","1.0"));
        resources.add(swaggerResource("resume-service","/api/resume/v2/api-docs","1.0"));
        resources.add(swaggerResource("apply-service","/api/apply/v2/api-docs","1.0"));
//        resources.add(swaggerResource("apply-service","/api/apply/v2/api-docs","1.0"));
        return resources;
    }
    private SwaggerResource swaggerResource(String name,String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }
}
