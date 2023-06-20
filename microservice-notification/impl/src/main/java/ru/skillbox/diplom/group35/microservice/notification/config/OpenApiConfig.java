package ru.skillbox.diplom.group35.microservice.notification.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

/**
 * OpenApiConfig
 *
 * @Author Tretyakov Alexandr
 */




@OpenAPIDefinition(
        info = @Info(
                title = "Microservice Notification",
                description = "Сервис оповещений"
        )
)
@SecurityScheme(
        name = "JWT",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer")
public class OpenApiConfig {
}
