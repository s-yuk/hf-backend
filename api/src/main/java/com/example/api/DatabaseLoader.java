package com.example.api;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.api.model.Role;
import com.example.api.model.entity.UserEntity;
import com.example.api.repo.UserRepo;

import lombok.extern.slf4j.Slf4j;

@Configuration @Slf4j
public class DatabaseLoader {
  private UserRepo userRepo;

  public DatabaseLoader(UserRepo userRepo) {
    this.userRepo = userRepo;
  }

  @Bean
  public CommandLineRunner initializeDatabase() {
    return args -> {
      UserEntity user1 = new UserEntity("email", "name", "password", "100", "10", 1, Role.USER);
      UserEntity user2 = new UserEntity("john@gmail.com", "john", "iamjohn", "0", "0", 2, Role.USER);
      UserEntity user3 = new UserEntity("alice@gmail.com", "alice", "iamalice", "0", "0", 1, Role.ADMIN);
      UserEntity user4 = new UserEntity("foo@gmail.com", "foo", "iamfoo", null, null, 1, Role.ADMIN);

      userRepo.saveAll(List.of(user1, user2, user3, user4));

      log.info("user1: {}, user2: {}, user3: {}, user4: {}", user1, user2, user3, user4);
    };
  }
}
