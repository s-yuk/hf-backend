// package com.example.api.service.Impl;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Service;

// import com.example.api.model.entity.UserEntity;
// import com.example.api.repo.UserRepo;
// import com.example.api.service.UserService;

// import lombok.AllArgsConstructor;

// @Service
// @AllArgsConstructor
// public class UserServiceImpl implements UserService, UserDetailsService {
//   private final PasswordEncoder passwordEncoder;
//   @Autowired
//   UserRepo userRepo;
//   private final static String USER_NOT_FOUND_MSG = "user with email %s not found";

//   @Override
//   public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//     return userRepo.findByEmail(email)
//             .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
//   }

//   @Override
//   public String signUp(UserEntity user) {
//     boolean userExists = userRepo.findByEmail(user.getEmail()).isPresent();
//     if (userExists) {
//       throw new IllegalStateException("email already taken");
//     }
//     user.setPassword(passwordEncoder.encode(user.getPassword()));
//     userRepo.save(user);
//     return "is work";
//   }
// }
