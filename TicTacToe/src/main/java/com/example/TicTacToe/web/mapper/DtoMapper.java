package com.example.TicTacToe.web.mapper;

import com.example.TicTacToe.domain.model.Field;
import com.example.TicTacToe.domain.model.Game;
import com.example.TicTacToe.web.dto.FieldDto;
import com.example.TicTacToe.web.dto.GameDto;

public class DtoMapper {


    public static FieldDto toDto(Field field) {
        FieldDto fieldDto = new FieldDto();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                fieldDto.getField()[i][j] = field.getField()[i][j];
            }
        }
        return fieldDto;
    }

    public static GameDto GameToDto(Game game) {
        return new GameDto(game.getUuid(), toDto(game.getField()), game.getCreateDate());
    }
}
