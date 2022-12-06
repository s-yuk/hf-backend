package com.example.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api.model.History;

@Repository
public interface HistoryRepo extends JpaRepository<History, Long> {}
