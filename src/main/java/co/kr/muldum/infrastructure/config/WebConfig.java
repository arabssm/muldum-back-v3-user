package co.kr.muldum.infrastructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 전역 CORS 정책을 등록하는 Web MVC 설정 클래스입니다.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final CorsProperties corsProperties;

    public WebConfig(CorsProperties corsProperties) {
        this.corsProperties = corsProperties;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        if (corsProperties.getAllowedOrigins() != null && !corsProperties.getAllowedOrigins().isEmpty()) {
            registry.addMapping("/**")
                    .allowedOrigins(corsProperties.getAllowedOrigins().toArray(new String[0]))
                    .allowedMethods(corsProperties.getAllowedMethods().toArray(new String[0]))
                    .allowedHeaders(corsProperties.getAllowedHeaders().toArray(new String[0]))
                    .allowCredentials(corsProperties.isAllowCredentials())
                    .maxAge(corsProperties.getMaxAge());
        }
    }
}