package com.example.TicTacToe.di.jwt;


import com.example.TicTacToe.domain.model.User;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.Data;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;


@Component
@Data
public class JwtProvider {


    private Duration lifetime = Duration.ofDays(1);
    private final SecretKey secret;

    public JwtProvider(@Value("${jwt.secret}") String secret) {
        this.secret = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateAccessToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        List<String> roleList = user.getUserRoles().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        claims.put("roles", roleList);
        claims.put("id", user.getId());
        Date issueDate = new Date();
        Date expiredDate = new Date(issueDate.getTime() + 300_000);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getLogin())
                .setIssuedAt(issueDate)
                .setExpiration(expiredDate)
                .signWith(secret)
                .compact();

    }

    public String generateRefreshToken(User user) {
        Date issueDate = new Date();
        Date expiredDate = new Date(issueDate.getTime() + lifetime.toMillis());

        return Jwts.builder()
                .claim("id", user.getId())
                .setIssuedAt(issueDate)
                .setExpiration(expiredDate)
                .signWith(secret)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secret)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public UUID getUserIdFromClaims(String token) {
        Claims claims = getClaims(token);
        return UUID.fromString(claims.get("id", String.class));
    }

}


