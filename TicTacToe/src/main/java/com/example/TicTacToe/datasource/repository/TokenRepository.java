package com.example.TicTacToe.datasource.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public interface TokenRepository {

    Map<UUID, String> tokenStorage = new HashMap<>();

    String findTokenByUserId(UUID userId);

    void saveToken(UUID userId, String token);

    void deleteToken(UUID userId);
}
