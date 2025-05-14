package com.example.TicTacToe.domain.model;

import lombok.Getter;

@Getter
public class Field {
    private final int[][] field;


    public Field() {
        field = new int[3][3];
        initField();
    }

    public void initField() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = -1;
            }
        }
    }
}
