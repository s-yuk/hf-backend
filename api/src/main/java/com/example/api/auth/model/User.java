package com.example.api.auth.model;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
@Table(name = "user")
public class User implements UserDetails {
  @Id @GeneratedValue(strategy = IDENTITY)
  @Column(name = "user_id")
  private Long id;
  @Column(name = "email", unique = true)
  private String email;
  @Column(name = "username")
  private String username;
  @Column(name = "password")
  private String password;
  @Column(name = "have_points", columnDefinition = "varchar(255) default '0'")
  private String have_points;
  @ManyToMany(fetch = EAGER)
  @JoinTable(
    name = "user_roles",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id")
)
  private Collection<Role> roles = new ArrayList<>();
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    // TODO Auto-generated method stub
    return null;
  }
  @Override
  public boolean isAccountNonExpired() {
    // TODO Auto-generated method stub
    return false;
  }
  @Override
  public boolean isAccountNonLocked() {
    // TODO Auto-generated method stub
    return false;
  }
  @Override
  public boolean isCredentialsNonExpired() {
    // TODO Auto-generated method stub
    return false;
  }
  @Override
  public boolean isEnabled() {
    // TODO Auto-generated method stub
    return false;
  }
}
