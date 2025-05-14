package com.example.TicTacToe.datasource.model;

import com.example.TicTacToe.datasource.converter.IntArrayToStringConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class FieldEntity {
    @Convert(converter = IntArrayToStringConverter.class)
    @Column(name = "field_state", columnDefinition = "TEXT")
    private final int[][] field;

    public FieldEntity() {
        field = new int[3][3];
    }
}

