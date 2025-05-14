package com.example.TicTacToe.web.controller;


import com.example.TicTacToe.datasource.service.TokenRepositoryService;
import com.example.TicTacToe.di.jwt.JwtProvider;
import com.example.TicTacToe.web.dto.JwtRequest;
import com.example.TicTacToe.web.dto.JwtResponse;
import com.example.TicTacToe.web.dto.SignUpRequest;
import com.example.TicTacToe.domain.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Controller
@RequestMapping()
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final TokenRepositoryService tokenRepositoryService;

    @GetMapping("/signUpPage")
    public String signUpPage() {
        return "signUpPage";
    }

    @PostMapping("/signUp")
    public ResponseEntity<?> signUpUser(@RequestBody SignUpRequest request) {
        try {
            if (authService.checkSizeLoginPassword(request.getLogin(), request.getPassword())) {
                String passwordHash = passwordEncoder.encode(request.getPassword());
                authService.createUser(request.getLogin(), passwordHash);
                return ResponseEntity.ok(Map.of("success", true));
            } else {
                return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Пароль и логин должны быть от 4 до 20 символов"));
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("success", false, "message", e.getMessage()));
        }
    }


    @GetMapping("/signInPage")
    public String signInPage() {
        return "signInPage";
    }


    @PostMapping("/signIn")
    public ResponseEntity<?> signInUser(@RequestBody JwtRequest signInRequest) {
        try {
            JwtResponse jwtResponse = authService.authorization(signInRequest);
            ResponseCookie accessTokenCookie = ResponseCookie.from("accessToken", jwtResponse.getAccessToken())
                    .httpOnly(true)
                    .secure(true)
                    .path("/")
                    .maxAge(Duration.ofSeconds(300))
                    .build();

            ResponseCookie refreshTokenCookie = ResponseCookie.from("refreshToken", jwtResponse.getRefreshToken())
                    .httpOnly(true)
                    .secure(true)
                    .path("/")
                    .maxAge(Duration.ofDays(1))
                    .build();

            return ResponseEntity.ok()
                    .header(HttpHeaders.SET_COOKIE, accessTokenCookie.toString())
                    .header(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString())
                    .body("success");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser(@CookieValue(name = "refreshToken", required = false) String token) throws URISyntaxException {
        if (token != null) {
            UUID userId = jwtProvider.getUserIdFromClaims(token);
            tokenRepositoryService.deleteToken(userId);
        }
        ResponseCookie deleteAccessTokenCookie = ResponseCookie.from("accessToken")
                .maxAge(0)
                .httpOnly(true).secure(true).path("/").build();
        ResponseCookie deleteRefreshTokenCookie = ResponseCookie.from("refreshToken")
                .maxAge(0)
                .httpOnly(true).secure(true).path("/").build();
        URI loginUri = new URI("/signInPage");
        return ResponseEntity.status(HttpStatus.SEE_OTHER)
                .location(loginUri)
                .header(HttpHeaders.SET_COOKIE, deleteAccessTokenCookie.toString())
                .header(HttpHeaders.SET_COOKIE, deleteRefreshTokenCookie.toString())
                .body("logout");
    }
}