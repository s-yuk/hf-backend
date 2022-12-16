package com.example.api.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.api.model.Role;

import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class UserEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private String name;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(unique = true)
  private String password;

  @Column(columnDefinition = "varchar(255) default '0'")
  private String havePoint;

  @Column(columnDefinition = "varchar(255) default '0'")
  private String haveStock;

  @Column(name = "groupId")
  private Integer groupId;

  @Enumerated(EnumType.STRING)
  private Role role;

  public UserEntity(String email, String name, String password, String havePoint, String haveStock, Integer groupId,
      Role role) {
    this.email = email;
    this.name = name;
    this.password = password;
    this.havePoint = havePoint;
    this.haveStock = haveStock;
    this.groupId = groupId;
    this.role = role;
  }

  public UserEntity() {}
}