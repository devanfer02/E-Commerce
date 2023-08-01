package com.example.ecommerce.configs;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Configuration
public class ApiKeyInterceptor implements HandlerInterceptor {

    @Value("${api.key}")
    private String apiKey;

    @Value("${api.key.name}")
    private String apiKeyName;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        if (handlerMethod.hasMethodAnnotation(ApiKeyNotRequired.class) ||
                handlerMethod.getBeanType().isAnnotationPresent(ApiKeyNotRequired.class)) {
            return true;
        }

        String providedApiKey = request.getHeader(apiKeyName);

        if(providedApiKey == null || !providedApiKey.equals(apiKey)) {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "Invalid API Key");
            return false;
        }

        return true;
    }

    public String toString() {
        return apiKey;
    }
}
