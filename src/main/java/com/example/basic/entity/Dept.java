package com.example.basic.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Dept {
    @Id
    Byte deptno;

    @Column(length = 14, nullable = false)
    String dname;

    @Column(length = 13, nullable = false)
    String loc;
}
