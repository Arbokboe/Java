package com.example.TicTacToe.datasource.service;

import com.example.TicTacToe.datasource.model.GameEntity;
import com.example.TicTacToe.datasource.repository.GameRepository;
import com.example.TicTacToe.datasource.repository.UserRepository;
import com.example.TicTacToe.di.jwt.JwtProvider;
import com.example.TicTacToe.web.dto.Statistic;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class StatisticService {

    private final UserRepository userRepository;
    private final GameRepository gameRepository;
    private final JwtProvider jwtProvider;

    public List<Statistic> getTopPlayers(int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        return userRepository.getStatisticLeaderBoard(pageable);
    }

    public List<GameEntity> getCompleteGamesByAccessToken(String accessToken) {
        return gameRepository.getCompleteGames(jwtProvider.getUserIdFromClaims(accessToken));
    }
}
