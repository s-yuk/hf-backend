package com.example.api.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api.model.Role;
import com.example.api.model.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, String> {
  User findByEmail(String email);

  Optional<List<User>> findAllByGroupIdAndRole(String groupId, Role role);

}
