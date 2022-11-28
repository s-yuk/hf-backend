package com.example.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.model.AvList;
import com.example.api.repo.AvListRepository;

@Service
public class AvListServiceImpl implements AvListService {

  @Autowired
  private AvListRepository _avListRepository;

  @Override
  public List<AvList> getAvLists() {
    return _avListRepository.findAll();
  };
  @Override
  public Optional<AvList> getAvListById(int id) {
    return _avListRepository.findById(id);
  };
  @Override
  public AvList saveAvList(AvList avList) {
    return _avListRepository.save(avList);
  };
  @Override
  public AvList updateAvListById(int id, AvList avList) {
    return _avListRepository.save(avList);
  };
  @Override
  public void removeAvListById(int id) {
    _avListRepository.deleteById(id);
  };
}
