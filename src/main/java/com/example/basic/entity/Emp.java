package com.example.basic.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Emp {
    @Id
    Integer empno;

    @Column(length = 10, nullable = false)
    String ename;

    @Column(length = 9, nullable = false)
    String job;

    @Column(nullable = false)
    Integer mgr;

    @Column(nullable = false)
    LocalDateTime hiredate;

    @Column(nullable = false)
    Integer sal;

    @Column(nullable = false)
    Integer comm;

    @ManyToOne
    @JoinColumn(name = "deptno", nullable = false)
    @JsonIgnore
    Dept dept;
}
