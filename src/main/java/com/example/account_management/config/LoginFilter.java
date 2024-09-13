package com.example.account_management.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class LoginFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // Log the request URL
        System.out.println("Request URL: " + request.getRequestURL().toString());
        // Proceed with the next filter
        filterChain.doFilter(request, response);
    }

    // @Override
    // public void init(FilterConfig filterConfig) throws ServletException {
    // Initialization code if needed
    // }

    @Override
    public void destroy() {
        // Cleanup code if needed
    }
}
