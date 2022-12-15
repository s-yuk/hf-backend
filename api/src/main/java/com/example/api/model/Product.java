// package com.example.api.model;

// import javax.persistence.Column;
// import javax.persistence.Entity;
// import javax.persistence.FetchType;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
// import javax.persistence.ManyToOne;
// import javax.persistence.Table;

// import lombok.AllArgsConstructor;
// import lombok.Data;
// import lombok.NoArgsConstructor;

// @Entity
// @Data
// @AllArgsConstructor
// @NoArgsConstructor
// @Table(name = "products")
// public class Product {
//   @Id
//   @GeneratedValue(strategy = GenerationType.IDENTITY)
//   @Column(name = "product_id")
//   private Long id;
//   @Column(name = "product_name")
//   private String product_name;
//   @Column(name = "necessary_points")
//   private String necessary_points;
//   @ManyToOne(fetch = FetchType.EAGER)
//   private User user;
// }
