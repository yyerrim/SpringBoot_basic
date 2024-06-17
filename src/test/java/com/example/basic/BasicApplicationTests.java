package com.example.basic;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
// junit : 클래스를 테스트 할 수 있게끔 지원해주는 라이브러리
// 단위(클래스단위/메소드단위) 테스트 : 전체 기능 중에 일정 부분만 테스트
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.basic.entity.Major;
import com.example.basic.entity.Player;
import com.example.basic.entity.Team;
import com.example.basic.repository.MajorRepository;
import com.example.basic.repository.PlayerRepository;
import com.example.basic.repository.TeamRepository;

@SpringBootTest // 건들지말기 (없어도 junit 동작은 함)
// 없으면 스프링에서 만들어놓은 Bean을 가져올 수 없기 때문에 스프링 테스트 불가

class BasicApplicationTests { // 보통 테스트 하려는 것과 관련된 이름으로 지음 ex) MajorRepositoryTest

	// 독립적으로 한 메소드만 실행하기 때문에 다른 코드를 막지 않아도 테스트 가능
	@Test // junit 테스트의 기본 모습
	void contextLoads() {
		System.out.println("테스트");
		// 실행 결과는 DEBUG CONSOLE - Launch Java Tests 에 출력됨
	}

	// 내가 뭘 테스트 하는지 명칭 지정 가능
	@Test
	void Major엔티티테스트() {
		System.out.println("테스트");
	}
	@Test
	void ServiceCenter레파지토리테스트() {
		System.out.println("테스트");
	}

	// 스프링 컨테이너에 만들어져있는 Bean을 연결해놓고 사용 가능
	@Autowired
	MajorRepository majorRepository;
	// @Test
	// void Major레파지토리테스트() {
	// 	Major major = new Major();
	// 	major.setName("아무거나");
	// 	// major.setName(""); // 255자로 설정되어있으니 256자 넣어보기
	// 	major.setEbtbDate(new Date());
	// 	majorRepository.save(major);
	// }
	// // 이대로 실행하면 maxPrsn이 NOT NULL이기 때문에 오류 발생
	@Test
	void Major레파지토리테스트() {
		Major major = new Major();
		major.setName("아무거나");
		major.setEbtbDate(new Date());
		major.setMaxPrsn(30);
		majorRepository.save(major);
	}

	@Autowired
	PlayerRepository playerRepository;
	@Test
	void PlayerRepositoryTest() {
		Team team = new Team(); // 꼼수
		team.setTeamId(1); // 진짜 존재하는 팀ID 적어줘야실행됨

		Player p = new Player(); // Player 테이블에 데이터 넣기 위해서 Player생성
		// p.setPlayerId(1);
		// p.setPlayerName("회원1");
		p.setPlayerId(2);
		p.setPlayerName("회원2");
		p.setTeam(team); // 실제 Team에 대한 데이터를 조회해서 넣어줘야함
		playerRepository.save(p);
	}

	@Autowired
	TeamRepository teamRepository;
	@Test
	void TeamRepositoryTest() {
		Team t = new Team(); // Player 테이블에 데이터 넣기 위해서 Player생성
		t.setTeamId(1);
		t.setTeamName("A팀");
		teamRepository.save(t);
	}

	// 회원2를 찾은 후 해당하는 팀명 출력
	@Test
	@Transactional // Entity에서 Player를 Lazy로 바꿨기 때문에 @Transactional을 적용해야 쿼리 2번 실행
	void PlayerRepository조회Test() {
		Optional<Player> opt = playerRepository.findById(2); // findById : optional 형태로 결과 나옴 -> Optional<Player> opt
		if (opt.isPresent()) { // 만약 opt가 값을 가지고 있다면
			Player p = opt.get(); // Player 데이터 꺼내오기
			System.out.println(p.getPlayerName()); // DEBUG CONSULE에 찍힘
			Team t = p.getTeam();
			System.out.println(t.getTeamName());
		}
		// 원래는 JOIN을 사용해서 해야함
	}

	// // 팀을 조회한 후 소속된 Player들의 이름 출력
	// @Test
	// // @Transactional // import : org.springframework.transaction.annotation.Transactional
	// // ===> Team Entity를 Eager로 바꿔놨기 때문에 @Transactional을 적용하지 않아도 오류 안남
	// void TeamRepository조회Test() {
	// 	Optional<Team> opt = teamRepository.findById(1);
	// 	if (opt.isPresent()) {
	// 		Team team = opt.get();
	// 		System.out.println(team.getTeamName());
	// 		List<Player> players = team.getPlayers();
	// 		for(Player p : players) {
	// 			System.out.println(p.getPlayerName());
	// 		}
	// 	}
	// }
	// JUnit은 DB에 1번 접속 한 후 연결을 끊어버림 : Lazy
	// -> 위의 코드는 쿼리 실행이 2번(findById, getPlayers)이기 때문에 오류 발생
	// DB 접속이 끊어져서 조회가 되지 않는 상황 : 1)@Transactional 사용 2)Lazy...를 Eager로 변경

	// 팀 조회를 한 후 소속된 플레이어가 누구 알고 싶으면 플레이어 테이블을 다시 select 하면서 팀 id로 플레이어 조회 ===> 쿼리 2번 실행
	// 플레이어를 조회해서 팀을 물어보면 처음부터 join을 하면서 동작 ===> 쿼리 1번 실행

	// many쪽에서는 연관관계에 있는 것을 처음부터 join으로 같이 조회
	// one쪽에서는 외래키가 없기 때문에 자기 데이터만 조회하고 그 이후에 필요하다면 쿼리문장을 한번 더 실행해서 데이터 갖고옴
	// === Lazy (뒤늦게 조회) : JPA에서는 효율성을 위해서 이 방식으로 감 - 사용할지도 안할지도 모르는 데이터를 처음부터 조회할 필요는 없기 때문
	// <---> Eager

	// 양방향일때 문제 발생
	// Team을 조회해서 Team이 갖고 있는 모든 데이터를 출력하라고 명령
	// Team 클래스가 갖고있는 toString 메소드가 호출됨
	// 이 Team toString 메소드는 players를 출력하라는 명령어가 있기 때문에 Player 클래스가 호출됨
	// 그러면 Player 클래스가 갖고있는 toString 메소드도 호출됨
	// 이 Player toString 메소드는 team을 출력하라는 명령어가 있기 때문에 다시 Team 클래스가 호출됨
	// 이 과정이 무한 반복되어버림(오류 메세지 : StackOverflowError)
	// ===> 한쪽의 연결고리를 끊어주면됨 : Player 클래스에 @ToString(exclude = "team")

	@Test
	// @Transactional // import : org.springframework.transaction.annotation.Transactional
	// ===> Team Entity를 Eager로 바꿔놨기 때문에 @Transactional을 적용 안해도 오류 안남
	void TeamRepository조회Test() {
		Optional<Team> opt = teamRepository.findById(1);
		if (opt.isPresent()) {
			Team team = opt.get();
			System.out.println(team);
		}
	}

}
