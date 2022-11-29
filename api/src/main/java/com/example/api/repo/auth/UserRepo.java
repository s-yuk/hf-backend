package com.example.api.repo.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api.model.auth.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
  User findByUsername(String username);
}
