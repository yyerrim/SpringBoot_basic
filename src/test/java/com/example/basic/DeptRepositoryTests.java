package com.example.basic;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.basic.entity.Dept;
import com.example.basic.entity.Emp;
import com.example.basic.entity.Hospital;
import com.example.basic.repository.DeptRepository;
import com.example.basic.repository.EmpRepository;
import com.example.basic.repository.HospitalRepository;

@SpringBootTest

class DeptRepositoryTests {
	@Autowired
	DeptRepository deptRepository;
	@Autowired
	EmpRepository empRepository;

	@Test
	void dept_조회() {
		List<Dept> list = deptRepository.findAll();
		System.out.println(list);
	}
	@Test
	void emp_조회() {
		List<Emp> list = empRepository.findAll();
		System.out.println(list);
	}

	@Test
	void emp_조회2() {
		Emp e = empRepository.findByEname("scott");
		System.out.println(e); // 이때 ToString 무한반복 오류 발생 가능 ===> exclude로 연결 끊어주면됨
	}

	@Test
	void emp_조회3() {
		String ename = "tt";
		Emp e = empRepository.findByEnameLike("%" + ename + "%");
		System.out.println(e);
	}

	@Test
	void emp_조회4() {
		List<Emp> e = empRepository.findBySalGreaterThan(2000);
		System.out.println(e);
	}

	// hospital 사용자 정의 메소드
	// sido 또는 name 에 "서울"이라는 단어가 포함되어 있는 병원 데이터 조회하기
	@Autowired
	HospitalRepository hospitalRepository;

	@Test
	void hospital_조회() {
		List<Hospital> e = hospitalRepository.findBySidoContainingOrNameContaining("서울", "서울");
		System.out.println(e);
	}
}
