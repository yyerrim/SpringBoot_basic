package com.example.basic.entity;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    Integer id;

    @Column(nullable = false)
    String sido;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    Integer medical;

    @Column(nullable = false)
    Integer room;

    @Column(nullable = false)
    String tel;

    @Column(nullable = false)
    String address;
}
