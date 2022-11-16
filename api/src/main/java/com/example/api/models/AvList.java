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
  @Column(name = "id", nullable = false)
  private int id;

  @Column(name = "av_title", nullable = false)
  private String av_title;

  @Column(name = "av_url", nullable = false)
  private String av_url;
}


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class users {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id", nullable = false)  //会員ID
  private int user_id;

  @size(min = 4, max = 15)
  @Column(name = "user_pass", nullable = true)  //パスワード
  private String user_pass;

  @Column(name = "user_mail", nullable = false)  //メールアドレス
  private String user_mail;

  @Column(name = "user_name", nullable = false)  //ニックネーム
  private String user_name;

  @Column(name = "user_point", nullable = false) //保有ポイント
  private String user_point = 0;

  @Column(name = "user_type_id", nullable = false)  //アカウント種別（親か子かを判別する為のコード）
  private String user_type_id;

  @Column(name = "user_group_id", nullable = false)  //所属グループID
  private String user_group_id;

  @Column(name = "user_image_id", nullable = true)  //アイコン画像ID
  private String user_image_id;
}



@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class products {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "product_id", nullable = false)  //商品ID
  private int product_id;

  @Column(name = "product_point", nullable = false)  //必要ポイント
  private String product_point;

  @Column(name = "product_image_id", nullable = true)  //商品画像ID
  private String product_image_id;

  @Column(name = "product_name", nullable = false)  //商品名
  private String product_name;

  @Column(name = "product_type_id", nullable = false)  //ジャンルID
  private String product_type_id;

}


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "history")
public class history {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "history_id", nullable = false)  //履歴ID
  private int history_id;

  @Column(name = "user_id", nullable = false)  //会員ID
  private String user_id;

  @Column(name = "product_id", nullable = true)  //商品ID
  private String product_id;

  @Column(name = "history_date", nullable = false)  //交換日
  private String history_date;

  @Column(name = "history_num", nullable = false)  //交換個数
  private String product_num;
}