package com.example.TicTacToe.web.controller;


import com.example.TicTacToe.datasource.service.UserRepositoryService;
import com.example.TicTacToe.domain.model.Game;
import com.example.TicTacToe.domain.model.User;
import com.example.TicTacToe.domain.service.GameServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@AllArgsConstructor
public class
GameController {

    private final GameServiceImpl gameService;
    private final UserRepositoryService userService;
    //Main страница

    @GetMapping("/")
    public String mainPage(@AuthenticationPrincipal UUID userId, Model model) {
        Optional<User> user = userService.findById(userId);
        user.ifPresent(value -> model.addAttribute("userLogin", value.getLogin()));
        return "mainPage";
    }


    //Логика игры с ботом

    @PostMapping("/NewGameWithBot")
    public String newGameWithBot() {
        Game game = gameService.newGameWithBot();
        return "redirect:/gameBot/" + game.getUuid();
    }

    @GetMapping("/gameBot/{gameId}")
    public String gamePageBot(@PathVariable UUID gameId, Model model) {
        gameService.showGame(gameId, model);
        return "NewGameWithBotPage";
    }

    @PostMapping("/gameBot/{gameId}")
    public String gameLoopBot(@PathVariable UUID gameId, @RequestParam("index") int index) {
        gameService.makeMove(gameId, index);
        return "redirect:/gameBot/" + gameId;
    }

    //Логика игры с человеком

    @PostMapping("/NewGameWithHuman")
    public String newGameWithHuman(@AuthenticationPrincipal UUID userId) {
        Game game = gameService.newGameWithHuman(userId);
        return "redirect:/gameHuman/" + game.getUuid();
    }

    @PostMapping("/gameHuman/{gameId}")
    public String gameLoopHuman(@PathVariable UUID gameId, @AuthenticationPrincipal UUID userId, @RequestParam("index") int index) {
        gameService.makeMoveGameWithHuman(gameId, userId, index);
        gameService.checkWinner(gameId);
        return "redirect:/gameHuman/" + gameId;
    }

    @GetMapping("/gameHuman/{gameId}")
    public String gamePageHuman(@PathVariable UUID gameId, Model model) {
        gameService.showGameWithHuman(gameId, model);
        return "NewGameWithHumanPage";
    }

    @GetMapping("/waitingGames")
    public String getWaitingGames(Model model) {
        List<Game> waitingGames = gameService.getWaitingGames();
        model.addAttribute("allWaitingGames", waitingGames);
        return "WaitingGamesPage";
    }

    @PostMapping("/joinGame")
    public String joinGame(@RequestParam UUID gameId, @AuthenticationPrincipal UUID userId, HttpServletResponse response) {
        if (gameService.verifyConnection(gameId, userId)) {
            gameService.joinUserToGame(gameId, userId);
            response.setStatus(HttpServletResponse.SC_OK);
            return "redirect:/gameHuman/" + gameId;
        }
        return "redirect:/waitingGames";
    }
}

