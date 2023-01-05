package com.example.api.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.api.model.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
  @Id
  private String id;

  private String username;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(unique = true)
  private String password;

  @Column(columnDefinition = "varchar(255) default '0'")
  private String havePoint = "0";

  @Column(columnDefinition = "varchar(255) default '0'")
  private String haveStock = "0";

  @Column(name = "groupId")
  private Integer groupId;

  @Enumerated(EnumType.STRING)
  private Role role;
}