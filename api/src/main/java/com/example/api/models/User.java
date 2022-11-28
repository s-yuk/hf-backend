package com.example.api.models.auth;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User {
  @Id
  @Column(name = "userid", length = 32, nullable = false)
  private String userId;

  // @Column(name = "email", nullable = false)
  // private String email;

  @Column(name = "password", length = 64, nullable = false)
  private String password;

  // @Column(name = "user_point", columnDefinition="varchar(255) default '0'", nullable = false)
  // private String user_point;

  // @Column(name = "group_id", nullable = false)
  // private String group_id;

  // @Column(name = "user_image_path", nullable = false)
  // private String user_image_path;

  @Column(name = "refreshtoken", length = 64)
  private String refreshToken;

  @Column(name = "refreshtoken_iat")
  private Instant refreshTokenIssuedAt;
}
