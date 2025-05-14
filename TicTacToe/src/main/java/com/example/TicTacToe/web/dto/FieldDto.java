package com.example.TicTacToe.web.dto;

import lombok.Getter;

@Getter
public class FieldDto {
    private final int[][] field;

    public FieldDto() {
        field = new int[3][3];
    }
}

