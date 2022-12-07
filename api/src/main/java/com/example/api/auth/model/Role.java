package com.example.api.auth.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static javax.persistence.GenerationType.*;

import javax.persistence.Column;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
@Table(name = "role")
public class Role {
  @Id  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "role_id")
  private Long id;
  @Column(name = "name")
  private String name;
}
