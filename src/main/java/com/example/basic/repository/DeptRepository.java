package com.example.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.basic.entity.Dept;

public interface DeptRepository extends JpaRepository<Dept, Byte> {

}
