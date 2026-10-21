package amelia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

/**
 * This class starts the Spring Boot application.
 */
@SpringBootApplication
public class ShopManagerApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(ShopManagerApplication.class, args);
    }
    @Bean
    public OpenAPI customOpenAPI()
    {
        return new OpenAPI()
                .info(new Info()
                        .title("Shop Manager API")
                        .version("1.0")
                        .description("REST API for the Shop Manager application"));
    }
}