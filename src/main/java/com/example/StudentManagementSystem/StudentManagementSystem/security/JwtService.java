package com.example.StudentManagementSystem.StudentManagementSystem.security;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    // Secret Key
    private static final String SECRET =
            "mysecretkeymysecretkeymysecretkey12";

    // Generate JWT Token
    public String generateToken(String username) {

        Key key = Keys.hmacShaKeyFor(
                SECRET.getBytes());

        return Jwts.builder()

                .setSubject(username)

                .setIssuedAt(new Date())

                .setExpiration(
                        new Date(
                                System.currentTimeMillis()
                                        + 1000 * 60 * 60))

                .signWith(
                        key,
                        SignatureAlgorithm.HS256)

                .compact();
    }
    
    public String extractUsername(String token) {

        Key key = Keys.hmacShaKeyFor(
                SECRET.getBytes());

        Claims claims = Jwts.parserBuilder()

                .setSigningKey(key)

                .build()

                .parseClaimsJws(token)

                .getBody();

        return claims.getSubject();
    }
}