package com.example.TicTacToe.domain.model;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class Game {

    private final UUID uuid;
    private final Field field;

    private UUID player1;
    private UUID player2;
    private UUID winPlayer;
    private Date createDate;

    public GameState state;

    public Game(UUID uuid, Field field, UUID player1, UUID player2, GameState state, UUID winPlayer, Date createDate) {
        this.uuid = uuid;
        this.field = field;
        this.player1 = player1;
        this.player2 = player2;
        this.state = state;
        this.winPlayer = winPlayer;
        this.createDate = createDate;
    }

    public Game(UUID player1, UUID player2, GameState state) {
        this.uuid = UUID.randomUUID();
        this.field = new Field();
        this.player1 = player1;
        this.player2 = player2;
        this.state = state;
        this.winPlayer = null;
        this.createDate = new Date();
    }

    public Game() {
        this.uuid = UUID.randomUUID();
        this.field = new Field();
        this.createDate = new Date();
    }

    public Game(UUID uuid, Field field, Date createDate) {
        this.uuid = uuid;
        this.field = field;
        this.createDate = createDate;
    }

    public void setPlayer(int row, int col) {
        field.getField()[row][col] = 1;
    }

    public void setMovePlayer1(int row, int col) {
        field.getField()[row][col] = 1;
    }

    public void setMovePlayer2(int row, int col) {
        field.getField()[row][col] = 0;
    }

    public void setOpponent(int row, int col) {
        field.getField()[row][col] = 0;
    }


}
