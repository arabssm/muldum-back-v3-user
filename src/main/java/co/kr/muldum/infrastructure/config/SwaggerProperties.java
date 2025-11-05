package co.kr.muldum.infrastructure.config;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

/**
 * Swagger/OpenAPI 문서화를 위한 메타데이터를 관리하는 프로퍼티 클래스입니다.
 */
@Validated
@ConfigurationProperties(prefix = "swagger")
public class SwaggerProperties {

    private static final String DEFAULT_TITLE = "Muldum API";
    private static final String DEFAULT_DESCRIPTION = "Muldum 프로젝트 API 명세서";
    private static final String DEFAULT_VERSION = "v1.0.0";

    private final String title;
    private final String description;
    private final String version;
    private final Server server;

    @ConstructorBinding
    public SwaggerProperties(
            @NotBlank String title,
            @NotBlank String description,
            @NotBlank String version,
            @Valid Server server
    ) {
        this.title = defaultIfBlank(title, DEFAULT_TITLE);
        this.description = defaultIfBlank(description, DEFAULT_DESCRIPTION);
        this.version = defaultIfBlank(version, DEFAULT_VERSION);
        this.server = server == null ? Server.defaults() : server;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getVersion() {
        return version;
    }

    public Server getServer() {
        return server;
    }

    private static String defaultIfBlank(String candidate, String defaultValue) {
        return (candidate == null || candidate.isBlank()) ? defaultValue : candidate;
    }

    /**
     * Swagger 서버 정보를 감싸는 중첩 프로퍼티입니다.
     */
    @Validated
    public static class Server {
        private static final String DEFAULT_URL = "http://localhost:8080";
        private static final String DEFAULT_DESCRIPTION = "개발 서버";

        @NotBlank
        private final String url;

        @NotBlank
        private final String description;

        @ConstructorBinding
        public Server(
                @NotBlank String url,
                @NotBlank String description
        ) {
            this.url = defaultIfBlank(url, DEFAULT_URL);
            this.description = defaultIfBlank(description, DEFAULT_DESCRIPTION);
        }

        public String getUrl() {
            return url;
        }

        public String getDescription() {
            return description;
        }

        private static Server defaults() {
            return new Server(DEFAULT_URL, DEFAULT_DESCRIPTION);
        }
    }
}
