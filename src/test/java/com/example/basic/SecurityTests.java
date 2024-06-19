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

}
