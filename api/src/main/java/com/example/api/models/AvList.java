package com.example.api.models;

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
@Table(name = "avlist")
public class AvList {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "product_id", nullable = false)
  private int product_id;

  @Column(name = "product_name", nullable = false)
  private String product_name;

  @Column(name = "product_point", nullable = false)
  private String product_point;

  @Column(name = "product_image_id", nullable = false)
  private String product_image_id;

}