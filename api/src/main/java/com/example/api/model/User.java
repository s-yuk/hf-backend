package com.example.api.model;

import java.util.Collection;
import java.util.Collections;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "users")
public class User implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;
  @Column(name = "email", unique = true)
  private String email;
  @Column(name = "name", unique = true)
  private String name;
  @Column(name = "password")
  private String password;
  @Column(name = "have_points", columnDefinition = "varchar(255) default '0'")
  private Integer have_points;
  @Column(name = "have_stocks", columnDefinition = "varchar(255) default '0'")
  private Integer have_stocks;
  @Column(name = "group_id")
  private Integer group_id;
  private Role role;

  public User(String email, String name, String password, Integer have_points, Integer have_stocks, Integer group_id,
      Role role) {
    this.email = email;
    this.name = name;
    this.password = password;
    this.have_points = have_points;
    this.have_stocks = have_stocks;
    this.group_id = group_id;
    this.role = role;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.name());
    return Collections.singletonList(authority);
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return false;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}