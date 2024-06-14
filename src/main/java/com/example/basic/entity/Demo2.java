package com.example.basic.entity;

import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

// @Entity : 적지 않으면 저장하고 서버 재구동해도 DB에 반영 안됨
// @Entity : primary key를 하나 이상 가져야함 (보통은 1개 권장)
@Entity
// @Table(name = "demo_a") : 클래스명과 다르게 테이블명을 지정하고 싶을 때 사용
@Data
public class Demo2 {
    @Id // primary key
    // @Id @GenaratedValue : 자동 증가
    long seq;

    // String user;

    String user2; // user ---> user2로 바뀜

    // String userName; : camel case로 설정한 경우 DB에는 user_name 형식으로 설정됨
    @Column(name = "username", length = 10, nullable = false) // _ 없이 설정됨
    String userName;

    int age;

    Date date1; // 세계 표준시 기준
    LocalDateTime date2; // 더 많이 사용함 : 지금 우리나라 시간
}
