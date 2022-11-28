package com.example.api.repo.auth;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.api.model.auth.User;

public interface UserRepo extends JpaRepository<User, Long> {
  User findByUserName(String username);
}
