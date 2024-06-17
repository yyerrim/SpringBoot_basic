package com.example.basic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.basic.entity.Emp;

public interface EmpRepository extends JpaRepository<Emp, Integer> {
    public Emp findByEname(String ename); // 조회2
    // 정확하게 이름이 일치하는 한명을 찾음

    public Emp findByEnameLike(String ename); // 조회3
    // 검색하려는 문자가 포함된 항목 찾음

    // SAL>2000 조회 ===> 여러명이기 때문에 List로 수정
    public List<Emp> findBySalGreaterThan(Integer sal); // 조회4
}
