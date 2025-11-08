package co.kr.muldum.infrastructure.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestPropertySource(properties = {
        "swagger.title=Test API",
        "swagger.description=Test Description",
        "swagger.version=v1.0.0",
        "swagger.server.url=http://localhost:8080",
        "swagger.server.description=Test Server",
        "cors.allowed-origins=http://localhost:3000",
        "cors.allowed-methods=GET,POST,PUT,DELETE",
        "cors.allowed-headers=*",
        "cors.allow-credentials=true",
        "cors.max-age=3600"
})
class ConfigBeansTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void webConfigBean이_정상_등록되어야_한다() {
        // given & when
        boolean exists = applicationContext.containsBean("webConfig");

        // then
        assertThat(exists).isTrue();
        assertThat(applicationContext.getBean("webConfig")).isInstanceOf(WebConfig.class);
    }

    @Test
    void swaggerConfigBean이_정상_등록되어야_한다() {
        // given & when
        boolean exists = applicationContext.containsBean("swaggerConfig");

        // then
        assertThat(exists).isTrue();
        assertThat(applicationContext.getBean("swaggerConfig")).isInstanceOf(SwaggerConfig.class);
    }

    @Test
    void corsPropertiesBean이_정상_등록되어야_한다() {
        // given & when
        boolean exists = applicationContext.containsBean("corsProperties");
        CorsProperties corsProperties = applicationContext.getBean(CorsProperties.class);

        // then
        assertThat(exists).isTrue();
        assertThat(corsProperties).isNotNull();
        assertThat(corsProperties.getAllowedOrigins()).contains("http://localhost:3000");
        assertThat(corsProperties.getAllowedMethods()).contains("GET", "POST", "PUT", "DELETE");
        assertThat(corsProperties.isAllowCredentials()).isTrue();
        assertThat(corsProperties.getMaxAge()).isEqualTo(3600);
    }

    @Test
    void swaggerPropertiesBean이_정상_등록되어야_한다() {
        // given & when
        boolean exists = applicationContext.containsBean("swaggerProperties");
        SwaggerProperties swaggerProperties = applicationContext.getBean(SwaggerProperties.class);

        // then
        assertThat(exists).isTrue();
        assertThat(swaggerProperties).isNotNull();
        assertThat(swaggerProperties.getTitle()).isEqualTo("Test API");
        assertThat(swaggerProperties.getDescription()).isEqualTo("Test Description");
        assertThat(swaggerProperties.getVersion()).isEqualTo("v1.0.0");
        assertThat(swaggerProperties.getServer().getUrl()).isEqualTo("http://localhost:8080");
        assertThat(swaggerProperties.getServer().getDescription()).isEqualTo("Test Server");
    }
}