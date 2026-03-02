package by.ino.caranalogapi.security;

import by.ino.caranalogapi.exception.AuthorizeException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class ApiKeyAspect {
    static final String CUSTOM_AUTH_HEADER = "X-API-Key";

    final HttpServletRequest request;

    @Value("${header.key.value}")
    private String headerKeyValue;

    @Before("@within(ApiKeyProtected) || @annotation(ApiKeyProtected)")
    public void checkApiKey() {
        String apiKey = request.getHeader(CUSTOM_AUTH_HEADER);
        if (!headerKeyValue.equals(apiKey)) {
            throw new AuthorizeException("Invalid API key");
        }
    }
}
