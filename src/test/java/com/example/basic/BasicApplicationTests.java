package com.example.basic;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
// junit : 클래스를 테스트 할 수 있게끔 지원해주는 라이브러리
// 단위(클래스단위/메소드단위) 테스트 : 전체 기능 중에 일정 부분만 테스트
import org.springframework.boot.test.context.SpringBootTest;

import com.example.basic.entity.Major;
import com.example.basic.repository.MajorRepository;

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

}
