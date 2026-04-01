package org.ptit.soccerrest.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI soccerRestOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("SoccerRest API")
                        .description("REST API để quản lý cầu thủ và huấn luyện viên bóng đá")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("PTIT")
                                .email("contact@ptit.edu.vn")));
    }
}

