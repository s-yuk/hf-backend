package com.example.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api.models.AvList;

@Repository
public interface AvListRepository extends JpaRepository<AvList, Integer> {}
