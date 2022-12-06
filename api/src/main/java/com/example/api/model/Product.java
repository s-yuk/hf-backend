package com.example.api.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.api.model.auth.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static javax.persistence.FetchType.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false)
  private int id;

  @Column(name = "product_point", nullable = false)
  private String product_point;

  @Column(name = "product_image_path", nullable = true)
  private String product_image_path;

  @Column(name = "product_name", nullable = false)
  private String product_name;

  @Column(name = "product_type_id", nullable = false)
  private String product_type_id;


  @ManyToMany(fetch = EAGER)
  private Collection<User> users = new ArrayList<>();

  // @OneToMany(mappedBy = "product")
  // private Collection<History> history;
}

