package com.example.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.basic.entity.Major;

public interface MajorRepository extends JpaRepository<Major, Integer>{
    
}
