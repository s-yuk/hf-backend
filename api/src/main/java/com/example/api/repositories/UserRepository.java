package com.example.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {}
