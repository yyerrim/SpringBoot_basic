package com.example.basic.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Data
public class Major {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO-INCREMENT 설정
    Integer id;

    @Column(length = 255, nullable = true)
    String name;

    @Column(name = "max_prsn", nullable = false)
    Integer maxPrsn;

    @Column(name = "edtb_date", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    Date ebtbDate;
}
