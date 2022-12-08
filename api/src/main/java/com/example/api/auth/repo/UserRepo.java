package com.example.api.auth.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api.auth.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
  User findByUsername(String username);

  User findUserByEmail(String email);

  Optional<User> findByEmail(String email);
}
