package com.mehmetsolak.urlshortener.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class SecurityKeyInterceptor implements HandlerInterceptor {

    @Value("${security.unprotected-routes.security-key}")
    private String expectedSecurityKey;

    @Override
    public boolean preHandle(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull Object handler
    ) {
        final String securityKey = request.getHeader("X-Security-Key");

        if(!expectedSecurityKey.equals(securityKey)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        return true;
    }
}
