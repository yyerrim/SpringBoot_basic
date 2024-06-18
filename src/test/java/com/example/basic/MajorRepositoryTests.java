package com.example.basic;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.basic.entity.Course;
import com.example.basic.entity.Major;
import com.example.basic.repository.CourseRepository;
import com.example.basic.repository.MajorRepository;

@SpringBootTest

class MajorRepositoryTests {
	// PDF 4.48 연습문제
	@Autowired
	MajorRepository majorRepository;
	@Autowired
	CourseRepository courseRepository;

	@Test
	void 데이터입력() {
		Major major = new Major();
		major.setName("IT");
		major.setMaxPrsn(100);
		major.setEbtbDate(new Date());
		majorRepository.save(major);

		Course course = new Course();
		course.setName("Java");
		course.setMajor(major);
		courseRepository.save(course);

		course = new Course();
		course.setName("SpringBoot");
		course.setMajor(major);
		courseRepository.save(course);
	}
	@Test
	void 데이터조회_Major() {
		List<Major> list = majorRepository.findAll();
		System.out.println(list);
	}
	@Test
	void 데이터조회_Course() {
		List<Course> list = courseRepository.findAll();
		System.out.println(list);
	}

	@Test
	void 데이터조회2_Course() {
		Optional<Course> opt = courseRepository.findById(1);
		Course course = opt.get();
		String name = course.getName();
		System.out.println(name);
		int id = course.getId();
		System.out.println(id);
		Major major = course.getMajor();
		System.out.println(major.getName());
	} // 아무 문제 없이 데이터 잘 나옴 : @ManyToOne
	// @OneToMany로 연결되어 있는 경우 오류 발생
	@Test
	void 데이터조회2_Major() {
		Optional<Major> opt = majorRepository.findById(6);
		Major major = opt.get();
		System.out.println(major.getName());
		System.out.println(major.getMaxPrsn());
		System.out.println(major.getEbtbDate());
		List<Course> list = major.getCourse_list();
		System.out.println(list);
	}

}
