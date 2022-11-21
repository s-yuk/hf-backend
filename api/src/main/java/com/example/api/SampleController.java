package com.example.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.models.User;
import com.example.api.services.user.UserService;

@RestController
public class SampleController {

  @Autowired
  private UserService _userService;

  @PostMapping("/sample")
  @CrossOrigin
  public ResultObject post(@RequestBody SampleForm sampleForm) {
    int id = sampleForm.getId();
    ResultObject resultObject = new ResultObject();
    resultObject.setName("zenn");
    resultObject.setLank(id);
    return resultObject;
  }

  @GetMapping("/test")
  public ResponseEntity<List<User>> get() {
    List<User> user = _userService.getUserName();
    return ResponseEntity.ok(user);
  }
}
