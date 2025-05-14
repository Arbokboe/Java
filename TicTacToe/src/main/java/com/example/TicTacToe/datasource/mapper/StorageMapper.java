package com.example.TicTacToe.datasource.mapper;

import com.example.TicTacToe.datasource.model.FieldEntity;
import com.example.TicTacToe.datasource.model.GameEntity;
import com.example.TicTacToe.domain.model.Field;
import com.example.TicTacToe.domain.model.Game;

public class StorageMapper {

    public static FieldEntity toEntity(Field field) {
        FieldEntity fieldEntity = new FieldEntity();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                fieldEntity.getField()[i][j] = field.getField()[i][j];
            }
        }
        return fieldEntity;
    }

    public static Field toField(FieldEntity fieldEntity) {
        Field field = new Field();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field.getField()[i][j] = fieldEntity.getField()[i][j];
            }
        }
        return field;
    }

    public static GameEntity GameToEntity(Game game) {
        return new GameEntity(game.getUuid(), toEntity(game.getField()), game.getPlayer1(), game.getPlayer2(), game.getState(), game.getWinPlayer(), game.getCreateDate());
    }

    public static Game EntityToGame(GameEntity gameEntity) {
        return new Game(gameEntity.getUuid(), toField(gameEntity.getField()), gameEntity.getPlayer1(), gameEntity.getPlayer2(), gameEntity.getState(), gameEntity.getWinPlayer(), gameEntity.getCreateDate());
    }

}
