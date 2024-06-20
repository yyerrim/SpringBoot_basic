package com.example.basic;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class SecurityTests {
	@Autowired
	PasswordEncoder passwordEncoder;

	@Test
	void passwordCreate() {
		String encPwd = passwordEncoder.encode("1234");
		log.info("encPwd {}", encPwd);
	}

	@Test
	void passwordCheck() {
		String rawPwd = "1234";
		String encodedPwd = "$2a$10$qPzmo6Ef/6TWX4kwVltSX.Vs89jyfcTXyAnL.197XftMSX.eJmlZW";
		boolean isMatch = passwordEncoder.matches(rawPwd, encodedPwd);
		log.info("match {}", isMatch);
	}

	@Test
	void 스프링암호화() {
		String pw = passwordEncoder.encode("1");
		System.out.println(pw);
		pw = passwordEncoder.encode("1");
		System.out.println(pw);
		// 실행되는 시간에 따라 암호화가 다르게 나옴 ===> DB에 들어간 데이터와 비교가 안됨
	}

}
