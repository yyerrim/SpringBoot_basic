package com.example.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.basic.entity.BoardFileTest;

public interface BoardFileTestRepository extends JpaRepository<BoardFileTest, Integer> {
}
