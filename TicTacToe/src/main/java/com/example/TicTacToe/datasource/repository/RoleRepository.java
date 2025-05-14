package com.example.TicTacToe.datasource.repository;

import com.example.TicTacToe.domain.model.UserRole;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<UserRole, Long> {

    public Optional<UserRole > findByName(String name);
}
