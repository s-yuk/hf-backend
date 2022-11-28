package com.example.api.repo.auth;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.api.model.auth.Role;

public interface RoleRepo extends JpaRepository<Role, Long> {
  Role findByName(String name);
}
