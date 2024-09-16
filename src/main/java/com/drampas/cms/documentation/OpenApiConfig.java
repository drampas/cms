package com.drampas.cms.documentation;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                description = "OpenApi documentation for content management API",
                title = "CMS - Drampas Aggelos"
        ),
        servers = {
        @Server(
                description = "Local ENV",
                url = "http://localhost:8080"
        )
        },
        security = @SecurityRequirement(name = "bearerToken")

)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT authorization",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in= SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}
