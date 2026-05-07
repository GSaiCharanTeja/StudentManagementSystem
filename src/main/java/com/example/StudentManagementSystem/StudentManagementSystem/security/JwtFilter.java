package com.example.StudentManagementSystem.StudentManagementSystem.security;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import java.util.Collections;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.context.SecurityContextHolder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtFilter
        extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(

            HttpServletRequest request,

            HttpServletResponse response,

            FilterChain filterChain)

            throws ServletException, IOException {

        String path = request.getServletPath();

        // Allow auth APIs
        if(path.startsWith("/auth")) {

            filterChain.doFilter(request, response);

            return;
        }

        String authHeader =
                request.getHeader("Authorization");

        if(authHeader == null ||
                !authHeader.startsWith("Bearer ")) {

            response.setStatus(
                    HttpServletResponse.SC_UNAUTHORIZED);

            response.getWriter().write(
                    "Missing or Invalid Token");

            return;
        }

        String token =
                authHeader.substring(7);

        try {

        	String username =
        	        jwtService.extractUsername(token);

        	UsernamePasswordAuthenticationToken authToken =
        	        new UsernamePasswordAuthenticationToken(
        	                username,
        	                null,
        	                Collections.emptyList());

        	SecurityContextHolder.getContext()
        	        .setAuthentication(authToken);

        	System.out.println(
        	        "Authenticated User: "
        	                + username);

        } catch(Exception e) {

            response.setStatus(
                    HttpServletResponse.SC_UNAUTHORIZED);

            response.getWriter().write(
                    "Invalid Token");

            return;
        }

        filterChain.doFilter(request, response);
    }
}