package com.warrioracademy.user.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "API Utilisateur", version = "1.0", description = "Documentation de l'API Utilisateur"))
public class SwaggerConfig {
}
