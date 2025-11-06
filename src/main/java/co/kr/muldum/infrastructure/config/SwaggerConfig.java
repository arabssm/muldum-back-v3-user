package co.kr.muldum.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    private final SwaggerProperties swaggerProperties;

    public SwaggerConfig(SwaggerProperties swaggerProperties) {
        this.swaggerProperties = swaggerProperties;
    }

    @Bean
    public OpenAPI openAPI() {
        OpenAPI openAPI = new OpenAPI()
                .info(new Info()
                        .title(swaggerProperties.getTitle())
                        .description(swaggerProperties.getDescription())
                        .version(swaggerProperties.getVersion()));

        // Server 설정이 있는 경우에만 추가
        if (swaggerProperties.getServer() != null
                && swaggerProperties.getServer().getUrl() != null) {
            openAPI.servers(List.of(
                    new Server()
                            .url(swaggerProperties.getServer().getUrl())
                            .description(swaggerProperties.getServer().getDescription())
            ));
        }

        return openAPI;
    }
}