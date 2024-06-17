package com.example.basic;

import java.util.Date;
import java.util.List;

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

}
