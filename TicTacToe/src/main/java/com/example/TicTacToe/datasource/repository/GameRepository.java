package com.example.TicTacToe.datasource.repository;

import com.example.TicTacToe.datasource.model.GameEntity;
import com.example.TicTacToe.domain.model.GameState;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.UUID;


public interface GameRepository extends CrudRepository<GameEntity, UUID> {

    /**
     * Находит GameEntity по состоянию игры. Spring Data JPA автоматически сгенерирует реализацию
     * этого метода по его названию ("findBy" + "State").
     *
     * @param state для поиска игры по ее состоянию.
     * @return Optional с GameEntity, если найден, иначе пустой Optional.
     */
    List<GameEntity> findByState(GameState state);


    /**
     * Возвращает список завершенных игр (выигрыши или ничьи) для указанного пользователя.
     * Игра считается завершенной для пользователя, если он участвовал И
     * (он выиграл как Player 1, ИЛИ он выиграл как Player 2, ИЛИ игра завершилась ничьей).
     * Результаты сортируются по дате создания по возрастанию.
     *
     * @param userId UUID пользователя.
     * @return Список завершенных игр (GameEntity).
     */
    @Query(" SELECT g FROM GameEntity g" +
            " WHERE (g.player1 = :userId OR g.player2 = :userId)" +
            " AND (" +
            " ( (g.player1 = :userId AND g.state = com.example.TicTacToe.domain.model.GameState.PLAYER_1_WINS) " +
            "   OR (g.player2 = :userId AND g.state = com.example.TicTacToe.domain.model.GameState.PLAYER_2_WINS) )" +
            " OR " +
            " (g.state = com.example.TicTacToe.domain.model.GameState.DRAW)" +
            " )" +
            " ORDER BY g.createDate ASC")
    List<GameEntity> getCompleteGames(@Param("userId") UUID userId);
}
