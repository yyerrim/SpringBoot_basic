package com.example.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.basic.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {

}
