package com.example.TicTacToe.datasource.repository;

import com.example.TicTacToe.domain.model.User;
import com.example.TicTacToe.web.dto.Statistic;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface UserRepository extends CrudRepository<User, Long> {
    /**
     * Находит пользователя по логину. Spring Data JPA автоматически сгенерирует реализацию
     * этого метода по его названию ("findBy" + "Login").
     *
     * @param login Логин для поиска.
     * @return Optional с пользователем, если найден, иначе пустой Optional.
     */
    Optional<User> findByLogin(String login);

    /**
     * Находит пользователя по айди. Spring Data JPA автоматически сгенерирует реализацию
     * этого метода по его названию ("findBy" + "Id").
     *
     * @param id айди для поиска.
     * @return Optional с пользователем, если найден, иначе пустой Optional.
     */
    Optional<User> findById(UUID id);

    /**
     * Находит пользователя по логину. Spring Data JPA автоматически сгенерирует реализацию
     * этого метода по его названию ("existsBy" + "Login").
     *
     * @param login логин для поиска.
     * @return true если логин существует и false в противном случае.
     */
    boolean existsByLogin(String login);

    @Query("SELECT NEW com.example.TicTacToe.web.dto.Statistic(u.id, " + // Убрали u.login
            "   CAST(" +
            "       (SELECT COUNT(g1) FROM GameEntity g1 WHERE (g1.player1 = u.id AND g1.state = com.example.TicTacToe.domain.model.GameState.PLAYER_1_WINS) OR (g1.player2 = u.id AND g1.state = com.example.TicTacToe.domain.model.GameState.PLAYER_2_WINS))" +
            "   AS double) " +
            "   / " +
            "   CASE WHEN " +
            "       ((SELECT COUNT(g2) FROM GameEntity g2 WHERE (g2.player1 = u.id AND g2.state = com.example.TicTacToe.domain.model.GameState.PLAYER_2_WINS) OR (g2.player2 = u.id AND g2.state = com.example.TicTacToe.domain.model.GameState.PLAYER_1_WINS)) + " + // Losses
            "        (SELECT COUNT(g3) FROM GameEntity g3 WHERE (g3.player1 = u.id OR g3.player2 = u.id) AND g3.state = com.example.TicTacToe.domain.model.GameState.DRAW)) = 0 " + // Draws
            "   THEN 1.0 " +
            "   ELSE " +
            "       CAST(" +
            "           (SELECT COUNT(g2) FROM GameEntity g2 WHERE (g2.player1 = u.id AND g2.state = com.example.TicTacToe.domain.model.GameState.PLAYER_2_WINS) OR (g2.player2 = u.id AND g2.state = com.example.TicTacToe.domain.model.GameState.PLAYER_1_WINS)) + " + // Losses
            "           (SELECT COUNT(g3) FROM GameEntity g3 WHERE (g3.player1 = u.id OR g3.player2 = u.id) AND g3.state = com.example.TicTacToe.domain.model.GameState.DRAW)" + // Draws
            "       AS double) " +
            "   END" +
            ") " +
            "FROM User u " +
            "WHERE EXISTS (SELECT g FROM GameEntity g WHERE g.player1 = u.id OR g.player2 = u.id) " +
            "ORDER BY 2 DESC")
    List<Statistic> getStatisticLeaderBoard(Pageable pageable);


}

