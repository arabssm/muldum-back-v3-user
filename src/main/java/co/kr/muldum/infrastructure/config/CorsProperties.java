package co.kr.muldum.infrastructure.config;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Objects;

/**
 * {@link org.springframework.web.servlet.config.annotation.CorsRegistry}에 주입할
 * CORS 관련 설정 값을 보관하는 프로퍼티 클래스입니다.
 */
@Validated
@ConfigurationProperties(prefix = "cors")
public class CorsProperties {

    private static final List<String> DEFAULT_ALLOWED_ORIGINS = List.of(
            "http://localhost:3000",
            "http://localhost:3001",
            "http://localhost:5173"
    );
    private static final List<String> DEFAULT_ALLOWED_METHODS = List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS");
    private static final List<String> DEFAULT_ALLOWED_HEADERS = List.of("*");
    private static final boolean DEFAULT_ALLOW_CREDENTIALS = true;
    private static final long DEFAULT_MAX_AGE = 3600L;
    private static final String HTTP_METHOD_REGEX = "^(GET|POST|PUT|DELETE|PATCH|OPTIONS|HEAD)$";

    private final List<String> allowedOrigins;
    private final List<String> allowedMethods;
    private final List<String> allowedHeaders;
    private final boolean allowCredentials;
    private final long maxAge;

    @ConstructorBinding
    public CorsProperties(
            List<@NotBlank String> allowedOrigins,
            List<@Pattern(regexp = HTTP_METHOD_REGEX, message = "지원하지 않는 HTTP Method 입니다.") String> allowedMethods,
            List<@NotBlank String> allowedHeaders,
            Boolean allowCredentials,
            @Min(value = 0, message = "CORS maxAge는 0 이상이어야 합니다.") Long maxAge
    ) {
        this.allowedOrigins = toUnmodifiableOrDefault(allowedOrigins, DEFAULT_ALLOWED_ORIGINS);
        this.allowedMethods = toUnmodifiableOrDefault(allowedMethods, DEFAULT_ALLOWED_METHODS);
        this.allowedHeaders = toUnmodifiableOrDefault(allowedHeaders, DEFAULT_ALLOWED_HEADERS);
        this.allowCredentials = allowCredentials != null ? allowCredentials : DEFAULT_ALLOW_CREDENTIALS;
        this.maxAge = maxAge != null && maxAge >= 0 ? maxAge : DEFAULT_MAX_AGE;
    }

    public List<String> getAllowedOrigins() {
        return allowedOrigins;
    }

    public List<String> getAllowedMethods() {
        return allowedMethods;
    }

    public List<String> getAllowedHeaders() {
        return allowedHeaders;
    }

    public boolean isAllowCredentials() {
        return allowCredentials;
    }

    public long getMaxAge() {
        return maxAge;
    }

    public String[] allowedOriginsArray() {
        return allowedOrigins.toArray(String[]::new);
    }

    public String[] allowedMethodsArray() {
        return allowedMethods.toArray(String[]::new);
    }

    public String[] allowedHeadersArray() {
        return allowedHeaders.toArray(String[]::new);
    }

    private static List<String> toUnmodifiableOrDefault(List<String> source, List<String> defaultValues) {
        if (source == null) {
            return defaultValues;
        }

        var cleaned = source.stream()
                .filter(Objects::nonNull)
                .map(String::strip)
                .filter(candidate -> !candidate.isEmpty())
                .toList();

        return cleaned.isEmpty() ? defaultValues : List.copyOf(cleaned);
    }
}
