package com.example.TicTacToe.web.controller;

import com.example.TicTacToe.datasource.model.GameEntity;
import com.example.TicTacToe.datasource.service.StatisticService;
import com.example.TicTacToe.web.dto.Statistic;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Data
public class StatisticController {

    private final StatisticService statisticService;

    @GetMapping("/leaderBoard")
    public ResponseEntity<List<Statistic>> getLeaderBoard(@RequestParam(defaultValue = "10") int limit) {
        List<Statistic> leaderBoard = statisticService.getTopPlayers(limit);
        return ResponseEntity.ok(leaderBoard);
    }

    @GetMapping("/completedGames")
    public ResponseEntity<List<GameEntity>> getHistoryGame(@CookieValue(name = "accessToken", required = false) String accessToken) {
        List<GameEntity> gameEntities = statisticService.getCompleteGamesByAccessToken(accessToken);
        return ResponseEntity.ok(gameEntities);
    }
}
