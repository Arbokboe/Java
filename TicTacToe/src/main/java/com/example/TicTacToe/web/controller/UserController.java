package com.example.TicTacToe.web.controller;

import com.example.TicTacToe.datasource.service.UserRepositoryService;
import com.example.TicTacToe.domain.model.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;
import java.util.UUID;

@Controller
@AllArgsConstructor
public class UserController {

    private final UserRepositoryService userService;

    @GetMapping("/profile")
    public String UserProfile(@AuthenticationPrincipal UUID userId) {
        return "redirect:/profile/" + userId;
    }

    @GetMapping("/profile/{userId}")
    public String ShowUserProfile(@PathVariable String userId, Model model) {
        Optional<User> user = userService.findById(UUID.fromString(userId));
        model.addAttribute("login", user.get().getLogin());
        return "userProfilePage";
    }
}
