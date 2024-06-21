package com.example.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.basic.entity.BoardTest;

public interface BoardTestRepository extends JpaRepository<BoardTest, Integer> {
}
