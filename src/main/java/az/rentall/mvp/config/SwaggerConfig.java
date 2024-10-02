package az.rentall.mvp.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI()
                .info(new Info()
                        .title("SecondLife")
                        .description("Api for people to give their items free")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Rizvan Mammadzada")
                                .email("rizvan.memmedov2004@gmail.com")
                                .url("https://gitlab.com/RizvanMammadzada/rentall")))
                .addSecurityItem(new SecurityRequirement().addList("JavaInUseSecuritySchema"))
                .components(new Components().addSecuritySchemes("JavaInUseSecuritySchema",new SecurityScheme()
                        .name("JavaInUseSecuritySchema").type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")));

    }
}
