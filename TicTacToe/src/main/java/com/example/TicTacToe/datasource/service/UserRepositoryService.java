package com.example.TicTacToe.datasource.service;

import com.example.TicTacToe.datasource.repository.RoleRepository;
import com.example.TicTacToe.datasource.repository.UserRepository;
import com.example.TicTacToe.domain.model.User;
import com.example.TicTacToe.domain.model.UserRole;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;



import java.util.Optional;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class UserRepositoryService {

    UserRepository userRepository;
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;

    /// USER

    public Optional<User> findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }

    public boolean checkPassword(User user, String password) {
        return passwordEncoder.matches(password, user.getPassword());
    }

    public String getUserLoginById(UUID id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(User::getLogin).orElse(null);
    }

    public boolean existsByLogin(String login) {
        return userRepository.existsByLogin(login);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    /// ROLE

    public void saveRole(UserRole role) {
        roleRepository.save(role);
    }

    public Optional<UserRole> findRoleByName(String name) {
        return roleRepository.findByName(name);
    }
}
