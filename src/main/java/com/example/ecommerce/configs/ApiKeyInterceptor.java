package com.example.ecommerce.configs;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;

@Configuration
public class ApiKeyInterceptor implements HandlerInterceptor {

    @Value("${api.key}")
    private String apiKey;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String providedApiKey = request.getHeader("X-API-Key");

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
