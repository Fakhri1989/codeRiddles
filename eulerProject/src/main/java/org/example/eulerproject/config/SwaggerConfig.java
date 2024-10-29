package org.example.eulerproject.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("User Data Access Layer API")
                        .description("API documentation for the User Data Access Layer")
                        .version("1.0")
                        .contact(new Contact()
                                .name("fakhri")
                                .url("https://www.eulerProject.com")
                                .email("fakhri.said1989@gmail.com"))
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("User Data Access Layer Documentation")
                        .url("https://yourwebsite.com/docs"));
    }
}
