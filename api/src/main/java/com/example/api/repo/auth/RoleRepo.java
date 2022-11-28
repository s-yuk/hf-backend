package com.example.api.repo.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api.model.auth.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
  Role findByName(String name);
}
