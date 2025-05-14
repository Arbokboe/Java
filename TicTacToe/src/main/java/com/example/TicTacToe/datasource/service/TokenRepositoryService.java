package com.example.TicTacToe.datasource.service;

import com.example.TicTacToe.datasource.repository.TokenRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class TokenRepositoryService implements TokenRepository {


    @Override
    public String findTokenByUserId(UUID userId) {
        return tokenStorage.get(userId);
    }

    @Override
    public void saveToken(UUID userId, String token) {
        tokenStorage.put(userId, token);
    }

    @Override
    public void deleteToken(UUID userId) {
        tokenStorage.remove(userId);
    }
}
