package com.example.SpringLaptop.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

/**
 * Configuracion Swagger para la generacion de documentacion de la API REST
 * http://localhost:8080/swagger-ui/
 * JSON: http://localhost:8080/v2/api-docs
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiDetails())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    public ApiInfo apiDetails(){
        return new ApiInfo("Spring Boot Laptop API REST",
                      "Libray Api Rest docs",
                         "1.0",
                 "https://www.google.es",
                                new Contact("Angel","https://www.google.es","angelmmgc@gmail.com"),
                         "MIT",
                       "https://www.google.es",
                                Collections.emptyList());
    }
}
