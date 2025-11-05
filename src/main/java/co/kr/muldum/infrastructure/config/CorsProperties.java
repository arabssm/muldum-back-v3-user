package co.kr.muldum.infrastructure.config;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Validated
@ConfigurationProperties(prefix = "cors")
public class CorsProperties {

    private static final List<String> DEFAULT_ALLOWED_ORIGINS = List.of("http://localhost:3000");
    private static final List<String> DEFAULT_ALLOWED_METHODS = List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS");
    private static final List<String> DEFAULT_ALLOWED_HEADERS = List.of("*");
    private static final boolean DEFAULT_ALLOW_CREDENTIALS = true;
    private static final long DEFAULT_MAX_AGE = 3600L;

    private final List<String> allowedOrigins;
    private final List<String> allowedMethods;
    private final List<String> allowedHeaders;
    private final boolean allowCredentials;
    private final long maxAge;

    @ConstructorBinding
    public CorsProperties(
            @NotEmpty List<String> allowedOrigins,
            @NotEmpty List<String> allowedMethods,
            @NotEmpty List<String> allowedHeaders,
            Boolean allowCredentials,
            @Min(0) Long maxAge
    ) {
        this.allowedOrigins = copyOrDefault(allowedOrigins, DEFAULT_ALLOWED_ORIGINS);
        this.allowedMethods = copyOrDefault(allowedMethods, DEFAULT_ALLOWED_METHODS);
        this.allowedHeaders = copyOrDefault(allowedHeaders, DEFAULT_ALLOWED_HEADERS);
        this.allowCredentials = allowCredentials != null ? allowCredentials : DEFAULT_ALLOW_CREDENTIALS;
        this.maxAge = maxAge != null && maxAge > 0 ? maxAge : DEFAULT_MAX_AGE;
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

    private static List<String> copyOrDefault(List<String> source, List<String> defaultValues) {
        var cleaned = source == null ? List.<String>of() : source.stream()
                .filter(Objects::nonNull)
                .map(String::trim)
                .filter(candidate -> !candidate.isEmpty())
                .toList();

        if (cleaned.isEmpty()) {
            return defaultValues;
        }

        return Collections.unmodifiableList(cleaned);
    }
}
