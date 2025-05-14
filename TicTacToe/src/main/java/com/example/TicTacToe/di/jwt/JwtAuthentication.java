package com.example.TicTacToe.di.jwt;


import com.example.TicTacToe.domain.model.UserRole;
import lombok.Data;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.UUID;

@Data
public class JwtAuthentication implements Authentication {


    private boolean isAuthenticated;
    private List<UserRole> roles;
    private UUID principal;

    JwtAuthentication(UUID principal, List<UserRole> roles, boolean isAuthenticated) {
        this.isAuthenticated = isAuthenticated;
        this.roles = roles;
        this.principal = principal;
    }

    @Override
    public List<UserRole> getAuthorities() {
        return roles;
    }


    @Override
    public Object getPrincipal() {
        return principal;
    }

    @Override
    public boolean isAuthenticated() {
        return isAuthenticated;
    }


    @Override
    public String getName() {
        return principal.toString();
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.isAuthenticated = isAuthenticated;
    }

    /// Не используется
    @Override
    public Object getDetails() {
        return null;
    }

    /// Не используется
    @Override
    public Object getCredentials() {
        return null;
    }
}
