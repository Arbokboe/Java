package com.example.TicTacToe.datasource.service;


import com.example.TicTacToe.datasource.mapper.StorageMapper;
import com.example.TicTacToe.datasource.model.GameEntity;
import com.example.TicTacToe.datasource.repository.GameRepository;
import com.example.TicTacToe.di.jwt.JwtProvider;
import com.example.TicTacToe.domain.model.Game;
import com.example.TicTacToe.domain.model.GameState;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class GameRepositoryService {

    private final GameRepository gameRepository;
    private final JwtProvider jwtProvider;

    public Optional<GameEntity> findByUUID(UUID gameId) {
        return gameRepository.findById(gameId);
    }

    public List<GameEntity> findByState(GameState state) {
        return gameRepository.findByState(state);
    }

    public void saveGameToGameEntity(Game game) {
        GameEntity gameEntity = StorageMapper.GameToEntity(game);
        gameRepository.save(gameEntity);
    }

    public void refreshGameInDB(Game game) {
        saveGameToGameEntity(game);
    }

    public Game getGameFromEntity(UUID gameId) {
        Optional<GameEntity> gameEntity = gameRepository.findById(gameId);
        Game currentGame = null;
        if (gameEntity.isPresent()) {
            currentGame = StorageMapper.EntityToGame(gameEntity.get());
        }
        return currentGame;
    }
}
