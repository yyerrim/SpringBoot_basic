package com.example.basic.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity(name = "table_exam_2")
public class TableExam2 {
    @Id
    Integer id;

    String name;

    @Temporal(TemporalType.DATE)
    Date birthDay;

    @Temporal(TemporalType.TIME)
    Date birthTime;

    @Temporal(TemporalType.TIMESTAMP)
    Date signupDate;
}
