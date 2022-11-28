package com.example.api.services;

import java.util.List;
import java.util.Optional;

import com.example.api.model.AvList;

public interface AvListService {
  public List<AvList> getAvLists();
  public Optional<AvList> getAvListById(int id);
  public AvList saveAvList(AvList avList);
  public AvList updateAvListById(int id, AvList avList);
  public void removeAvListById(int id);
}
