package com.example.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.model.dto.HandleErrorDto;
import com.example.api.model.form.UpdateMyChildPointForm;
import com.example.api.service.PropertyService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/property")
@AllArgsConstructor
@Slf4j
public class PropertyController {

  @Autowired
  PropertyService propertyService;

  @PutMapping("/point/{id}")
  public HandleErrorDto updateMyChildPoint(@PathVariable String id, @RequestBody UpdateMyChildPointForm form) {
    log.info("form:{}", form);
    HandleErrorDto dto = propertyService.updateMyChildPoint(id, form);
    return dto;
  }
}
