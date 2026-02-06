package com.recettes.Recettes.config;

import java.io.IOException;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Order(1)
public class RequestLoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        
        String method = request.getMethod();
        String path = request.getRequestURI();
        String queryString = request.getQueryString();
        String fullPath = queryString == null ? path : path + "?" + queryString;
        
        log.info("📥 Requête entrante: {} {}", method, fullPath);
        log.info("   Headers Authorization: {}", request.getHeader("Authorization"));
        log.info("   Origin: {}", request.getHeader("Origin"));
        
        try {
            filterChain.doFilter(request, response);
            log.info("📤 Réponse: {} {} - Status: {}", method, fullPath, response.getStatus());
        } catch (Exception e) {
            log.error("❌ Erreur lors du traitement de {} {}: {}", method, fullPath, e.getMessage());
            throw e;
        }
    }
}
