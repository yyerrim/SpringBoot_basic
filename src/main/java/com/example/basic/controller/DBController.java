// Presentation Layer
package com.example.basic.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.basic.dao.DemoDao;
import com.example.basic.mapper.DemoMapper;
import com.example.basic.mapper.EmpMapper;

@RestController
public class DBController {
    @Autowired
    DemoDao demoDao;
    @GetMapping("/jdbc/demo")
    public List<Map<String, Object>> jdbcDemo() {
        return demoDao.select();
    }

    // MyBatis
    // 1. 라이브러리 추가 : pom.xml
    // 2. xml 위치 지정 : application.properties
    // 3. mapper(dao) 위치 지정 : @Configuration
    // 4. mapper 클래스 작성
    // 5. xml (sql) 파일 작성
    @Autowired
    DemoMapper demoMapper;
    @GetMapping("/mybatis/demo")
    public List<Map<String, Object>> mybatisDemo() {
        return demoMapper.select();
    }

    // emp 테이블 데이터 불러오기 : 1.2.3.은 기본 설정이므로 4.5.만 추가하면됨
    @Autowired
    EmpMapper empMapper;
    @GetMapping("/mybatis/emp")
    public List<Map<String, Object>> mybatisEmp(
            @RequestParam("ename") String ename) {
        return empMapper.select(ename);
    }

    // // 데이터 입력하는 mybatis 코드
    // @GetMapping("/mybatis/demo/add")
    // public String mybatisDemoAdd(
    //         @RequestParam Map<String, Object> map) {
    //     int result = demoMapper.insert(map);
    //     if (result == 0) {
    //         return "데이터 추가 실패";
    //     } else {
    //         return "데이터 추가 성공";
    //     }
    // }
    // "데이터 추가 실패" 뜨게 하려면 try-catch로 해줘야함
    @GetMapping("/mybatis/demo/add")
    public String mybatisDemoAdd(
            @RequestParam Map<String, Object> map) {
        try {
            demoMapper.insert(map);
        } catch (Exception e) { // SQLIntegrityConstraintViolationException
            return "데이터 추가 실패";
        }
        return "데이터 추가 성공";
    }

}
