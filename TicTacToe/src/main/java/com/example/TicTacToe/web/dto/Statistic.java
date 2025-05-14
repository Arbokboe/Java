package com.example.TicTacToe.web.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class Statistic {
    private final UUID id;
    private final double wiRatio;

    public Statistic(UUID id, double wiRatio) {
        this.id = id;
        this.wiRatio = wiRatio;
    }
}
