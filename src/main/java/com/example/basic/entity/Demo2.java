package com.example.basic.entity;

import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

// @Entity : 적지 않으면 저장하고 서버 재구동해도 DB에 반영 안됨
// @Entity : primary key가 있어야함
@Entity
@Data
public class Demo2 {
    @Id
    long seq;
    // String user;
    String user2; // user ---> user2로 바뀜
    int age;
    Date date1; // 세계 표준시 기준
    LocalDateTime date2; // 더 많이 사용함 : 지금 우리나라 시간
}
