package com.example.talentPortal.config;


import com.example.talentPortal.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.NonNull;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.time.Instant;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    public static final String AUTH_ERROR_ATTR = "AUTH_ERROR_MESSAGE";

    private final ObjectMapper objectMapper;
//
    public CustomAuthenticationEntryPoint(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void commence(
            HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull AuthenticationException authException
    ) throws IOException {

        String message = (String) request.getAttribute(AUTH_ERROR_ATTR);
        if (message == null || message.isBlank()) {
            message = "Unauthorized";
        }

        ErrorResponse body = new ErrorResponse(
                Instant.now(),
                HttpServletResponse.SC_UNAUTHORIZED,
                "Unauthorized",
                message,
                request.getRequestURI()
        );

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        objectMapper.writeValue(response.getOutputStream(), body);
//        response.getWriter().write("{\"error\":\"Unauthorized\"}");
    }
}
