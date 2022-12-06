package com.example.api.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.model.History;
import com.example.api.repo.HistoryRepo;
import com.example.api.service.HistoryService;

@Service
public class HistoryServiceImpl implements HistoryService {
  @Autowired
  private HistoryRepo _historyRepo;

  @Override
  public List<History> getHistories() {
    return _historyRepo.findAll();
  }
}
