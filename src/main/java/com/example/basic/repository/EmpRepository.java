package com.example.basic.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.basic.entity.Emp;

public interface EmpRepository extends JpaRepository<Emp, Integer> {
    // // JPA : application.properties의 설정을 바꾸면 DB 종류에 관계없이 기존 코드를 그대로 실행할 수 있음

    // // 사용자정의 메소드로는 도저히 해결이 안될때 사용
    // @Query(nativeQuery = true, value = "select * from emp")
    // // nativeQuery의 단점 : DB에 따라서 동작하지 않는 기능이 만들어질수있음
    // @Query(value = "select e from emp e") : JPQL

    public Emp findByEname(String ename); // 조회2
    // 정확하게 이름이 일치하는 한명을 찾음

    public Emp findByEnameLike(String ename); // 조회3
    // 검색하려는 문자가 포함된 항목 찾음

    // SAL>2000 조회 ===> 여러명이기 때문에 List로 수정
    public List<Emp> findBySalGreaterThan(Integer sal); // 조회4

    public List<Emp> findByEnameLike(String ename, Pageable pageable);
    // Pageable pageable : 마지막 매개변수로 넣어줘야함

    // 이름으로 오름차순 정렬 : Sort 보다 우선순위 높음
    public List<Emp> findByEnameLikeOrderByEnameAsc(String ename, Pageable pageable);

}
