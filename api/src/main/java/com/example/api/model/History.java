package com.example.api.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class History {
  @Id @GeneratedValue()
  private Long id;
  private String product_name;
  private LocalDateTime buyDateTime;
  private String haveproduct;
  private String havepoint;
  private Long user_id;
  private Long product_id;
}
