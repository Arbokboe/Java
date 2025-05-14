package com.example.TicTacToe.web.dto;

import lombok.Data;

@Data
public class JwtRequest {
    String username;
    String password;
}
