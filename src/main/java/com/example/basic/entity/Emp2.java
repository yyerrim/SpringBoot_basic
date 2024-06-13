package com.example.basic.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Emp2 {
    @Id
    int empno; // primary key로 지정되어야함
    String ename;
    String job;
    int mgr;
    LocalDateTime hiredate;
    int sal;
    int comm;
    byte deptno;
}
