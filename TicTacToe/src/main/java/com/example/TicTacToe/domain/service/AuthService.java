package com.example.TicTacToe.domain.service;

import com.example.TicTacToe.datasource.service.TokenRepositoryService;
import com.example.TicTacToe.datasource.service.UserRepositoryService;
import com.example.TicTacToe.di.jwt.JwtProvider;
import com.example.TicTacToe.domain.model.User;
import com.example.TicTacToe.web.dto.JwtRequest;
import com.example.TicTacToe.web.dto.JwtResponse;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;


@Service
@AllArgsConstructor
public class AuthService {

    UserRepositoryService userService;
    JwtProvider jwtProvider;
    TokenRepositoryService tokenService;


    public void createUser(String login, String passwordHash) {
        if (userService.existsByLogin(login)) {
            throw new IllegalArgumentException("Логин '" + login + "' уже существует");
        }
        User newUser = new User(login, passwordHash);
        newUser.addUserRole(userService.findRoleByName("ROLE_USER").get());
        userService.save(newUser);
    }

    public boolean checkSizeLoginPassword(String login, String password) {
        return login.length() > 3 && login.length() <= 20 && password.length() > 3 && password.length() <= 20;
    }


    public JwtResponse authorization(JwtRequest jwtRequest) {
        Optional<User> userOptional = userService.findByLogin(jwtRequest.getUsername());
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("Пользователь с таким логином не найден");
        }
        User user = userOptional.get();

        if (!userService.checkPassword(user, jwtRequest.getPassword())) {
            throw new IllegalArgumentException("Неправильный пароль");
        }
        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setAccessToken(jwtProvider.generateAccessToken(user));
        jwtResponse.setRefreshToken(jwtProvider.generateRefreshToken(user));
        tokenService.saveToken(user.getId(), jwtResponse.getAccessToken());

        return jwtResponse;
    }

    public JwtResponse updateToken(String refreshToken) {
        JwtResponse jwtResponse = new JwtResponse();

        Claims claims = jwtProvider.getClaims(refreshToken);
        UUID userId = UUID.fromString(claims.get("id", String.class));
        User user = userService.findById(userId).get();
        tokenService.deleteToken(user.getId());
        jwtResponse.setAccessToken(jwtProvider.generateAccessToken(user));
        jwtResponse.setRefreshToken(jwtProvider.generateRefreshToken(user));
        tokenService.saveToken(user.getId(), jwtResponse.getRefreshToken());
        return jwtResponse;
    }


}
