package com.example.basic.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Team {
    @Id
    int teamId;
    String teamName;

    // @OneToMany(mappedBy = "team") // 팀 조회 후 소속된 선수가 누구누구인지 알고싶을때 ===> 테이블 모양이 달라지지는 않음
    // // mappedBy = "연결되어야하는 상대방의 변수명"
    // List<Player> players = new ArrayList<>(); // 상대방은 여럿이기 때문에 List 형태로 바꿔줘야함

    @OneToMany(mappedBy = "team", fetch = FetchType.EAGER)
    List<Player> players = new ArrayList<>();

    // // @Data가 자동으로 toString() 생성
    // @Override
    // public String toString() {
    //     return teamId + teamName + players;
    // }
}
