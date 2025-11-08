package co.kr.muldum.infrastructure.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 전역 CORS 정책을 등록하는 Web MVC 설정 클래스입니다.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private static final Logger log = LoggerFactory.getLogger(WebConfig.class);

    private final CorsProperties corsProperties;

    public WebConfig(CorsProperties corsProperties) {
        this.corsProperties = corsProperties;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        var allowedOrigins = corsProperties.allowedOriginsArray();
        if (allowedOrigins.length == 0) {
            log.warn("CORS 허용 Origin 정보가 비어 있어 매핑을 건너뜁니다.");
            return;
        }

        registry.addMapping("/**")
                .allowedOrigins(allowedOrigins)
                .allowedMethods(corsProperties.allowedMethodsArray())
                .allowedHeaders(corsProperties.allowedHeadersArray())
                .allowCredentials(corsProperties.isAllowCredentials())
                .maxAge(corsProperties.getMaxAge());

        log.info("CORS 허용 Origin: {}", String.join(", ", allowedOrigins));
    }
}
