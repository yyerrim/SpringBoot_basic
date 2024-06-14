package com.example.basic.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "table_exam_1") // @Entity 안에 name 써줘도 테이블명 지정 가능
@Data
public class TableExam1 {
    @Id
    // @GeneratedValue
    // table_exam_1_seq 만들어짐
    // - AUTO-INCREMENT : 옵션 - 자동증가에 다음번 숫자가 뭔지 적혀있음
    // 이런 기능이 없는 경우 자동증가 할때 하나의 또 다른 테이블을 만들어서 숫자 1을 넣어둠
    // 다음 데이터를 넣을때 2, 그 다음 데이터를 넣을때 3이 저장됨
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // PDF 4.37 ~ 38 참고
    // 처음에 서버를 띄우면 50개 정도의 정보를 캐시메모리에 올려둠 (효율성을 위해서)
    // 서버를 재구동하면 앞의 메모리가 사라지고 그 이후부터 증가시킨다는 정보를 새로 올림
    // @GeneratedValue(strategy = GenerationType.IDENTITY) : 설정을 해주면 상관없이 1씩 증가
    Integer id;

    @Column(length = 100, nullable = false)
    String title;

    @Column(name = "description", length = 1000, nullable = true)
    String content;

    Long price;

    String brand;

    String name;
}
