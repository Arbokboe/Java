package com.example.TicTacToe.domain.service;

import com.example.TicTacToe.domain.model.Game;
import org.springframework.ui.Model;

import java.util.List;
import java.util.UUID;

public interface GameService {

    int[] nextMove(Game currentGame);

    boolean validMove(Game currentGame, int index);

    int isGameOver(int[][] board);

    Game newGameWithBot();

    Game newGameWithHuman(UUID userId);

    void showGame(UUID uuid, Model model);

    void makeMove(UUID uuid, int index);

    void joinUserToGame(UUID uuid, UUID userId);

    List<Game> getWaitingGames();

    void makeMoveGameWithHuman(UUID uuid, UUID userId, int index);

    void checkWinner(UUID gameId);

    void showGameWithHuman(UUID uuid, Model model);

    void setMove(Game currentGame, UUID moveId, int index);

}
