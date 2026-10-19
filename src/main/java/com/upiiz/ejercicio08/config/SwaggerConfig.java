package com.upiiz.ejercicio08.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.extensions.Extension;
import io.swagger.v3.oas.annotations.extensions.ExtensionProperty;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API de Cursos",
                description = "API REST para administrar cursos (CRUD) con HATEOAS y respuestas JSON estandarizadas",
                termsOfService = "https://www.zacatecas.ipn.mx/",
                contact = @Contact(
                        name = "Juan Pablo Honorato López",
                        email = "jhonoratol2000@alumno.ipn.mx",
                        url = "https://www.zacatecas.ipn.mx/",
                        extensions = {
                                @Extension(properties = {
                                        @ExtensionProperty(name = "Escuela", value = "UPIIZ")
                                })
                        }
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.apache.org/licenses/LICENSE-2.0"
                ),
                version = "1.0.0"
        ),
        servers = {
                @Server(description = "Servidor local", url = "http://localhost:8080"),
                @Server(description = "Servidor de producción", url = "https://api.ejemplo.com")
        }
)
public class SwaggerConfig {
}
