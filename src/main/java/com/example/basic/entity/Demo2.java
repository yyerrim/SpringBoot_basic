package com.example.basic.entity;

import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
// primary key를 하나 이상 가져야함 (보통은 1개 권장)

// @Table(name = "demo_a") : 클래스명과 다르게 테이블명을 지정하고 싶을 때 사용
@Data
public class Demo2 {
    @Id // primary key 설정
    // @GenaratedValue : 자동 증가
    long seq;

    // String user; ---> String user2;
    String user2; // user -> user2로 바뀜

    // 참조자료형은 null 허용이 기본값
    @Column(name = "username", length = 10, nullable = false)
    // 따로 이름 지정해주지 않으면 camel case(userName)가 snake case(user_name)로 설정됨
    String userName;

    int age;

    Date date1; // 세계 표준시 기준
    LocalDateTime date2; // 지금 우리나라 시간 (더 많이 사용)
}
