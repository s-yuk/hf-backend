package com.example.api.auth.service.Impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.api.auth.service.ResourceService;
import com.example.api.model.Role;
import com.example.api.repo.RoleRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ResourceServiceImpl implements ResourceService {
  private final RoleRepo roleRepo;

  @Override
  public Role saveRole(Role role) {
    log.info("saving new role {} to the database", role.getName());
    return roleRepo.save(role);
  }
}