package com.example.TicTacToe.di.security;

import com.example.TicTacToe.di.jwt.JwtProvider;
import com.example.TicTacToe.di.jwt.JwtUtil;
import com.example.TicTacToe.domain.service.AuthService;
import com.example.TicTacToe.web.dto.JwtResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.time.Duration;


@Component
@AllArgsConstructor
public class AuthFilter extends GenericFilterBean {

    private final JwtProvider jwtProvider;
    private final AuthService authService;


    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String path = httpRequest.getRequestURI();

        if (path.endsWith("/signUp") || path.endsWith("/signUpPage") || path.endsWith("/signIn") ||
                path.endsWith("signInPage") || path.endsWith("/login") || path.endsWith("/favicon.ico")) {
            filterChain.doFilter(request, response);
            return;
        }
        String accessToken = getAccessTokenFromCookie(httpRequest, "accessToken");
        String refreshToken = getAccessTokenFromCookie(httpRequest, "refreshToken");

        if (jwtProvider.validateToken(accessToken)) {
            SecurityContextHolder.getContext().setAuthentication(JwtUtil.createJwtAuthentication(jwtProvider.getClaims(accessToken)));
        } else if (jwtProvider.validateToken(refreshToken)) {
            JwtResponse jwtResponse = authService.updateToken(refreshToken);
            ResponseCookie newAccessTokenCookie = ResponseCookie.from("accessToken", jwtResponse.getAccessToken())
                    .httpOnly(true).secure(true).path("/").maxAge(Duration.ofSeconds(300)).build();
            ResponseCookie newRefreshTokenCookie = ResponseCookie.from("refreshToken", jwtResponse.getRefreshToken())
                    .httpOnly(true).secure(true).path("/").maxAge(Duration.ofDays(1)).build();
            httpResponse.addHeader(HttpHeaders.SET_COOKIE, newAccessTokenCookie.toString());
            httpResponse.addHeader(HttpHeaders.SET_COOKIE, newRefreshTokenCookie.toString());
            SecurityContextHolder.getContext().setAuthentication(JwtUtil.createJwtAuthentication(jwtProvider.getClaims(jwtResponse.getAccessToken())));
        } else {
            httpResponse.setHeader("WWW-Authenticate", "Bearer");
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        filterChain.doFilter(request, response);
    }


    private String getAccessTokenFromCookie(HttpServletRequest request, String name) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (name.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

}





