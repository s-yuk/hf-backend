package com.example.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api.model.AvList;

@Repository
public interface AvListRepository extends JpaRepository<AvList, Integer> {}
