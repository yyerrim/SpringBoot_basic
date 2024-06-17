package com.example.basic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.basic.entity.Hospital;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {
    public List<Hospital> findBySidoContainingOrNameContaining(String sido, String name);
}
