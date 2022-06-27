package com.jet.restaurants.service.openclose.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().components(new Components()).info(new Info().title("Restaurant Management Service API")
                .description("This microservice handles the status change request from the external application "
                        + "via rest endpoints and with the help of KAFKA layer generates the "
                        + "event to other microservices that listens to the event to update the status."));
    }
}
