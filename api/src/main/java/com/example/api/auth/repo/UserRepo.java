package com.example.api.auth.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.api.auth.model.User;

@Repository
@Transactional
public interface UserRepo extends JpaRepository<User, Long> {
  User findByUsername(String username);
  Optional<User> findByEmail(String email);
}
