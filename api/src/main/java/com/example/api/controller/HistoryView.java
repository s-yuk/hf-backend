package com.example.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.model.History;
import com.example.api.service.HistoryService;

@RestController
@RequestMapping("/api/view")

public class HistoryView {
  @Autowired
  private HistoryService historyService;
  @GetMapping()
  public ResponseEntity<List<History>>getHistory() {
    return ResponseEntity.ok().body(historyService.getHistories());
  }

}
