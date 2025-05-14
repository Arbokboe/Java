package com.example.TicTacToe.di.jwt;

import com.example.TicTacToe.domain.model.UserRole;
import io.jsonwebtoken.Claims;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class JwtUtil {

    public static JwtAuthentication createJwtAuthentication(Claims claims) {
        String userId = claims.get("id", String.class);
        List<String> roles = claims.get("roles", List.class);
        List<UserRole> userRoles = convertStringRolesToUserRoles(roles);
        return new JwtAuthentication(UUID.fromString(userId), userRoles, true);
    }



    private static List<UserRole> convertStringRolesToUserRoles(List<String> roles) {
        List<UserRole> userRoles = new ArrayList<>();

        for (String role : roles) {
            UserRole userRole = new UserRole();
            userRole.setName(role);
            userRole.setId((long) 1);
            userRoles.add(userRole);
        }
        return userRoles;
    }
}
