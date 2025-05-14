package com.example.TicTacToe.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false, columnDefinition = "UUID")
    UUID id;
    @Column(nullable = false, unique = true, length = 15)
    String login;
    @Column(nullable = false, length = 100)
    String password;
    String MoveSymbol;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    List<UserRole> UserRoles = new ArrayList<>();

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User(String login, String password, String MoveSymbol) {
        this.login = login;
        this.password = password;
        this.MoveSymbol = MoveSymbol;
    }

    public void addUserRole(UserRole role) {
        UserRoles.add(role);
    }


}
