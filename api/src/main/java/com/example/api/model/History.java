package com.example.api.model;

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
@Table(name = "history")
public class History {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "history_id", nullable = false)
  private int history_id;

  @Column(name = "user_id", nullable = false)
  private String user_id;

  @Column(name = "product_id", nullable = true)
  private String product_id;

  @Column(name = "history_date", nullable = false)
  private String history_date;

  @Column(name = "history_num", nullable = false)
  private String history_num;
}
