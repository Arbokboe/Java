package com.example.TicTacToe.web.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class GameDto {

    private final UUID uuid;
    private final FieldDto field;
    private final Date createDate;


    public GameDto(UUID id, FieldDto field, Date createDate) {
        this.uuid = id;
        this.field = field;
        this.createDate = createDate;

    }
}
