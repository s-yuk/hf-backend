package com.example.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
  Optional<User> findByUserId(String userId);
}
