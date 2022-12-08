package com.example.api.auth.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api.auth.model.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
  Role findByName(String name);
}
