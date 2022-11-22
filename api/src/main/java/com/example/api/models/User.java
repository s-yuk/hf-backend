package com.example.api.models;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id", nullable = false)
  private int user_id;

  @Column(name = "email", nullable = false)
  private String email;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "user_name", nullable = false)
  private String user_name;

  @Column(name = "user_point", columnDefinition="varchar(255) default '0'", nullable = false)
  private String user_point;

  @Column(name = "group_id", nullable = false)
  private String group_id;

  @Column(name = "user_image_path", nullable = false)
  private String user_image_path;

  @Column(name = "refreshtoken", length = 64)
  private String refreshToken;

  @Column(name = "refreshtoken_iat")
  private Instant refreshTokenIssuedAt;
}
