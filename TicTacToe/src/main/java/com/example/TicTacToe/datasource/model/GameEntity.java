package com.example.TicTacToe.datasource.model;

import com.example.TicTacToe.domain.model.GameState;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
public class GameEntity {
    @Id
    private UUID uuid;

    @Embedded
    @Column(unique = false, nullable = false, updatable = true)
    private FieldEntity field;
    @Column(unique = false)
    private UUID player1;
    @Column(unique = false)
    private UUID player2;
    @Column(unique = false, name = "Winner")
    private UUID winPlayer;
    @Column(name = "Date")
    private Date createDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    GameState state;

    public GameEntity(UUID id, FieldEntity field, Date createDate) {
        this.uuid = id;
        this.field = field;
        this.createDate = createDate;
    }

    public GameEntity(UUID id, FieldEntity field, UUID player1, UUID player2, GameState state, UUID winPlayer, Date createDate) {
        this.uuid = id;
        this.field = field;
        this.player1 = player1;
        this.player2 = player2;
        this.winPlayer = winPlayer;
        this.state = state;
        this.createDate = createDate;
    }

}
