package com.example.TicTacToe.domain.service;

import com.example.TicTacToe.datasource.mapper.StorageMapper;
import com.example.TicTacToe.datasource.model.GameEntity;
import com.example.TicTacToe.datasource.service.GameRepositoryService;
import com.example.TicTacToe.datasource.service.UserRepositoryService;
import com.example.TicTacToe.domain.model.Game;
import com.example.TicTacToe.domain.model.GameState;
import com.example.TicTacToe.web.mapper.DtoMapper;
import com.example.TicTacToe.web.dto.GameDto;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class GameServiceImpl implements GameService {


    GameRepositoryService gameRepositoryService;
    UserRepositoryService userRepositoryService;

    private final int player1 = 1;  //X
    private final int player2 = 0;  //0
    private final int empty = -1;


    //Логика для игры с ботом
    @Override
    public int[] nextMove(Game currentGame) {
        int row = -1;
        int col = -1;
        int best_score = Integer.MIN_VALUE;
        int[][] field = currentGame.getField().getField();
        for (int rowCount = 0; rowCount < 3; rowCount++) {
            for (int colCount = 0; colCount < 3; colCount++) {
                if (field[rowCount][colCount] == empty) {
                    field[rowCount][colCount] = 0;
                    int score = minimax(field, 0, false);
                    field[rowCount][colCount] = empty;
                    if (score > best_score) {
                        best_score = score;
                        row = rowCount;
                        col = colCount;
                    }
                }
            }
        }
        if (row == -1 && col == -1) return null;
        else return new int[]{row, col};
    }

    @Override
    public boolean validMove(Game currentGame, int index) {
        return currentGame.getField().getField()[index / 3][index % 3] == empty;
    }

    @Override
    @Transactional
    public Game newGameWithBot() {
        Game game = new Game();
        gameRepositoryService.saveGameToGameEntity(game);
        return game;
    }

    @Override
    @Transactional
    public void showGame(UUID uuid, Model model) {
        Game currentGame = gameRepositoryService.getGameFromEntity(uuid);
        GameDto gameDto = DtoMapper.GameToDto(currentGame);
        model.addAttribute("field", gameDto.getField().getField());
        int gameState = isGameOver(currentGame.getField().getField());
        if (gameState == 10) {
            model.addAttribute("GameOver", true);
        } else if (gameState == -10) {
            model.addAttribute("Win", true);
        } else if (gameState == 0) {
            model.addAttribute("Draw", true);
        }
    }

    @Override
    @Transactional
    public void makeMove(UUID uuid, int index) {
        Game currentGame = gameRepositoryService.getGameFromEntity(uuid);
        if (validMove(currentGame, index)) {
            currentGame.setPlayer(index / 3, index % 3);
            if (isGameOver(currentGame.getField().getField()) == -1) {
                currentGame.setOpponent(nextMove(currentGame)[0], nextMove(currentGame)[1]);
            }
            gameRepositoryService.saveGameToGameEntity(currentGame);
        }
    }

    private int minimax(int[][] field, int depth, boolean isMaximizing) {
        if (isGameOver(field) != -1) {
            return isGameOver(field);
        }
        int best_score;
        if (isMaximizing) {
            best_score = Integer.MIN_VALUE;
            for (int rowCount = 0; rowCount < 3; rowCount++) {
                for (int colCount = 0; colCount < 3; colCount++) {
                    if (field[rowCount][colCount] == -1) {
                        field[rowCount][colCount] = 0;
                        int score = minimax(field, depth + 1, false);
                        field[rowCount][colCount] = -1;
                        if (score > best_score) {
                            best_score = score;
                        }
                    }
                }
            }
        } else {
            best_score = Integer.MAX_VALUE;
            for (int rowCount = 0; rowCount < 3; rowCount++) {
                for (int colCount = 0; colCount < 3; colCount++) {
                    if (field[rowCount][colCount] == -1) {
                        field[rowCount][colCount] = 1;
                        int score = minimax(field, depth + 1, true);
                        field[rowCount][colCount] = -1;
                        if (score < best_score) {
                            best_score = score;
                        }
                    }
                }
            }
        }
        return best_score;
    }

    //Логика для игры с человеком
    @Override
    @Transactional
    public Game newGameWithHuman(UUID userId) {
        Game game = new Game(userId, null, GameState.WAITING_PlAYERS);
        gameRepositoryService.saveGameToGameEntity(game);
        return game;
    }

    @Override
    public void setMove(Game currentGame, UUID moveId, int index) {
        if (moveId.equals(currentGame.getPlayer1())) {
            currentGame.setMovePlayer1(index / 3, index % 3);
        }
        if (moveId.equals(currentGame.getPlayer2())) {
            currentGame.setMovePlayer2(index / 3, index % 3);
        }
    }

    private void swapPlayersTurn(Game currentGame) {
        GameState currentState = currentGame.getState();
        if (currentState == GameState.PLAYER1_TURNS) {
            currentGame.setState(GameState.PLAYER2_TURNS);
        } else {
            currentGame.setState(GameState.PLAYER1_TURNS);
        }
    }

    private boolean checkQueue(Game currentGame, UUID moveId) {
        UUID Player1 = currentGame.getPlayer1();
        UUID Player2 = currentGame.getPlayer2();
        return Player1.equals(moveId) && currentGame.getState() == GameState.PLAYER1_TURNS || Player2.equals(moveId) && currentGame.getState() == GameState.PLAYER2_TURNS;
    }

    @Override
    @Transactional
    public void showGameWithHuman(UUID uuid, Model model) {
        Game currentGame = gameRepositoryService.getGameFromEntity(uuid);
        GameDto gameDto = DtoMapper.GameToDto(currentGame);
        GameState state = currentGame.getState();
        model.addAttribute("field", gameDto.getField().getField());
        if (state == GameState.PLAYER_2_WINS) {
            model.addAttribute("WinPlayer2", true);
            model.addAttribute("Player2", userRepositoryService.getUserLoginById(currentGame.getPlayer2()));
        } else if (state == GameState.PLAYER_1_WINS) {
            model.addAttribute("WinPlayer1", true);
            model.addAttribute("Player1", userRepositoryService.getUserLoginById(currentGame.getPlayer1()));
        } else if (state == GameState.DRAW) {
            model.addAttribute("Draw", true);
        }
    }

    @Override
    @Transactional
    public void checkWinner(UUID gameId) {
        Game currentGame = gameRepositoryService.getGameFromEntity(gameId);
        switch (isGameOver(currentGame.getField().getField())) {
            case 10:
                currentGame.setWinPlayer(currentGame.getPlayer2());
                currentGame.setState(GameState.PLAYER_2_WINS);
                gameRepositoryService.refreshGameInDB(currentGame);
                break;
            case -10:
                currentGame.setWinPlayer(currentGame.getPlayer1());
                currentGame.setState(GameState.PLAYER_1_WINS);
                gameRepositoryService.refreshGameInDB(currentGame);
                break;
            case 0:
                currentGame.setState(GameState.DRAW);
                gameRepositoryService.refreshGameInDB(currentGame);
                break;
        }
    }

    @Override
    @Transactional
    public void makeMoveGameWithHuman(UUID uuid, UUID userId, int index) {
        Game currentGame = gameRepositoryService.getGameFromEntity(uuid);
        if (currentGame.getPlayer2() != null) {
            if (validMove(currentGame, index) && checkQueue(currentGame, userId) && isGameOver(currentGame.getField().getField()) == -1) {
                setMove(currentGame, userId, index);
                swapPlayersTurn(currentGame);
                gameRepositoryService.saveGameToGameEntity(currentGame);
            }
        }
    }

    @Override
    @Transactional
    public List<Game> getWaitingGames() {
        List<GameEntity> gameEntities = gameRepositoryService.findByState(GameState.WAITING_PlAYERS);
        List<Game> waitingGames = new ArrayList<>();
        for (GameEntity gameEntity : gameEntities) {
            waitingGames.add(StorageMapper.EntityToGame(gameEntity));
        }
        return waitingGames;
    }

    //Метод для подключения игрока к игре
    @Override
    @Transactional
    public void joinUserToGame(UUID uuid, UUID userId) {
        Optional<GameEntity> gameEntity = gameRepositoryService.findByUUID(uuid);
        Game currentGame;
        if (gameEntity.isPresent()) {
            currentGame = StorageMapper.EntityToGame(gameEntity.get());
            currentGame.setState(GameState.PLAYER1_TURNS);
            currentGame.setPlayer2(userId);
            gameRepositoryService.saveGameToGameEntity(currentGame);
        }
    }

    @Transactional
    public boolean verifyConnection(UUID gameID, UUID userId) {
        Game currentGame = gameRepositoryService.getGameFromEntity(gameID);
        return currentGame.getPlayer2() == null || currentGame.getPlayer2().equals(userId);
    }

    //Общий метод для проверки состояния игры
    @Override
    public int isGameOver(int[][] field) {
        for (int rowCount = 0; rowCount < 3; rowCount++) {
            int player1RowCounter = 0;
            int player2RowCounter = 0;
            int player1ColCounter = 0;
            int player2ColCounter = 0;
            for (int colCount = 0; colCount < 3; colCount++) {
                if (field[rowCount][colCount] == player1) player2RowCounter++;
                else if (field[rowCount][colCount] == player2) player1RowCounter++;

                if (field[colCount][rowCount] == player1) player2ColCounter++;
                else if (field[colCount][rowCount] == player2) player1ColCounter++;
            }
            if (player1RowCounter == 3 || player1ColCounter == 3) return 10;
            if (player2RowCounter == 3 || player2ColCounter == 3) return -10;
        }

        if (field[0][0] == player1 && field[1][1] == player1 && field[2][2] == player1 || field[0][2] == player1 && field[1][1] == player1 && field[2][0] == player1)
            return -10;
        if (field[0][0] == player2 && field[1][1] == player2 && field[2][2] == player2 || field[0][2] == player2 && field[1][1] == player2 && field[2][0] == player2)
            return 10;

        boolean isDraw = true;
        for (int rowCount = 0; rowCount < 3; rowCount++) {
            for (int colCount = 0; colCount < 3; colCount++) {
                if (field[rowCount][colCount] == empty) {
                    isDraw = false;
                    break;
                }
            }
            if (!isDraw) break;
        }
        if (isDraw) return 0;

        return -1;
    }
}
