package com.huayue.userservice;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
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
@ConditionalOnClass(value = {SwaggerConfig.class})
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        ParameterBuilder builder = new ParameterBuilder();
        List<Parameter> parameters = new ArrayList<>();
        builder.name("Authorization")
                .description("User Authorization")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build();
        parameters.add(builder.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.huayue.userservice.controller"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(parameters);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("用户信息服务")
                .description("")
                .termsOfServiceUrl("")
                .version("1.0")
                .build();
    }
}
