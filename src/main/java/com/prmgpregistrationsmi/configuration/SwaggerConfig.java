package com.prmgpregistrationsmi.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "GP Registrations MI", version = "v1"), servers = @Server(url = "/"))
@SecurityScheme(name = "apiKey", type = SecuritySchemeType.APIKEY)
public class SwaggerConfig {
}
