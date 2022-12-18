package com.example.api.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.api.model.entity.User;
import com.example.api.model.form.RegistrationForm;
import com.example.api.repo.UserRepo;
import com.example.api.service.RegistrationService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {
  @Autowired
  private UserRepo userRepo;

  public String signUp(RegistrationForm form) {
    log.info("service {}", form);
    User user = new User();
    user.setName(form.getName());
    user.setEmail(form.getEmail());
    user.setPassword(form.getPassword());

    userRepo.save(user);
    return "service is work";
  }
}
