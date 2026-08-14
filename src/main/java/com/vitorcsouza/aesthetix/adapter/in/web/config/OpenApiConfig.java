package com.vitorcsouza.aesthetix.adapter.in.web.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("Aesthetix API")
                        .version("v1")
                        .description("API para sistema Aesthetix")
                        .contact(new Contact().name("Aesthetix Team"))
                        .license(new License().name("MIT"))
                );
    }
}