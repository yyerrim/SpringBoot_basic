package com.example.basic.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.ToString;

@Entity
@Data

@ToString(exclude = "team")

public class Player {
    @Id
    int playerId;
    String playerName;

    // @ManyToOne // Player : Many // 선수 조회 후 어느 팀인지 알고싶을때
    // @JoinColumn(name="team_id") // 명칭 지정
    // Team team;
    // // team_team_id : 내가 작성한 변수명 + 상대방 기본키
    // // Team team22; 로 바꿀 경우 : 원래는 기존에 있는 외래키명을 바꾸는게 맞는데 새로운 외래키가 생김
    // // -> 잘못만들어진거니까 Player 테이블 삭제 후 재구동하기

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    @JsonIgnore // : player에서는 team 출력 제외
    Team team;

    // // @Data가 자동으로 toString() 생성
    // @Override
    // public String toString() {
    // return playerId + playerName + team;
    // }
}
