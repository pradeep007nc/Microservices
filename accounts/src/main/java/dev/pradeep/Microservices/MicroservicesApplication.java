package dev.pradeep.Microservices;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@OpenAPIDefinition(
        info = @Info(
                title = "Accounts Microservice REST API",
                version = "1.0",
                description = "Accounts Microservice API",
                contact = @Contact(
                        name = "Pradeep",
                        email = "pradeepnaidu2486@gmail.com",
                        url = "https://github.com/pradeep007nc"

                ),
                license = @License(
                        name = "Apache2.0",
                        url = "https://github.com/pradeep007nc"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "dummy",
                url = "http://localhost:8080/swagger-ui/index.html"
        )
)
public class MicroservicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroservicesApplication.class, args);
    }

}
