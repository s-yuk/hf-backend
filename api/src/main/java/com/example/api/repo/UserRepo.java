package com.example.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api.model.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, String> {
  User findByEmail(String email);
}
