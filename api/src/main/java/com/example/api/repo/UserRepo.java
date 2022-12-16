package com.example.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api.model.entity.UserEntity;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long> {
  UserEntity findByEmail(String email);
}
