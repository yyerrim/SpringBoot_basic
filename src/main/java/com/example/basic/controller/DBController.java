// Presentation Layer
package com.example.basic.controller;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.basic.dao.DemoDao;
import com.example.basic.entity.Hospital;
import com.example.basic.entity.Player;
import com.example.basic.entity.ServiceCenter;
import com.example.basic.entity.TableExam1;
import com.example.basic.entity.Team;
import com.example.basic.mapper.DemoMapper;
import com.example.basic.mapper.EmpMapper;
import com.example.basic.repository.HospitalRepository;
import com.example.basic.repository.PlayerRepository;
import com.example.basic.repository.ServiceCenterRepository;
import com.example.basic.repository.TableExam1Repository;
import com.example.basic.repository.TeamRepository;

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

    // 데이터 입력하는 mybatis 코드
    @GetMapping("/mybatis/demo/add")
    public String mybatisDemoAdd(
            @RequestParam Map<String, Object> map) {
        try {
            demoMapper.insert(map);
        } catch (Exception e) { // SQLIntegrityConstraintViolationException
            return "데이터 추가 실패";
        }
        // int result = demoMapper.insert(map);
        // if (result == 0) {
        //     return "데이터 추가 실패";
        // } else {
        //     return "데이터 추가 성공";
        // }
        return "데이터 추가 성공";
    }

    @Autowired
    TableExam1Repository tableExam1Repository;

    // PDF 4.24
    // @GetMapping("/table1/add")
    // public String table1Add() {
    //     TableExam1 t1 = new TableExam1();
    //     tableExam1Repository.save(t1); // save(repository가 담당하고 있는 entity)
    //     return "입력 완료";
    // }
    // // 1. id가 자동증가가 아니기 때문에 값을 넣어줘야함 ===> 지금은 기능 수정을 위해 자동증가로 바꾼 상태
    // // ===> 위의 코드는 넣은 값이 없기 때문에 실행하면 식별자가 없다는 오류 발생
    // // 2. title이 NOT NULL이기 때문에 오류 발생    
    // @GetMapping("/table1/add")
    // public String table1Add() {
    //     TableExam1 t1 = new TableExam1();
    //     t1.setId(1);
    //     t1.setTitle("아무거나");
    //     tableExam1Repository.save(t1);
    //     return "입력 완료";
    // }
    // 기능 수정 - id : 자동증가, title : @RequestParam으로 넘겨받은 데이터 입력
    @GetMapping("/table1/add")
    public String table1Add(
            @RequestParam String title) {
        TableExam1 t1 = new TableExam1();
        // 얘는 데이터를 건별로 넣어야하기 때문에 Bean으로 만들지 않음
        // Bean은 스프링 메모리에 계속 존재하고 있으므로 여러명이 사용하게되면 꼬일 수 있음
        t1.setTitle(title);
        tableExam1Repository.save(t1);
        return "입력 완료";
    }
    // 이미 존재하고 있는 id를 지정해서 add 하면 해당하는 데이터의 내용만 바뀌게됨
    // 존재하지 않는 id를 지정해서 add 하면 새로 데이터 생김

    // PDF 4.25
    @GetMapping("/table1/list")
    public List<TableExam1> table1Find() {
        List<TableExam1> list = tableExam1Repository.findAll();
        return list;
    }
    // 1개 조회
    @GetMapping("/table1/{id}")
    public TableExam1 table1Find2(
            @PathVariable int id) {
        Optional<TableExam1> exam1 = tableExam1Repository.findById(id);
        // Optional 안에 있는 데이터를 뽑아 내는게 t.get()
        return exam1.get();
    }

    // PDF 4.27 : 에 있는 방법은 편법
    // 정석 : 1. 데이터베이스에 있는 객체를 조회 2.조회된 데이터를 delete
    @GetMapping("/table1/remove")
    public String table1Remove(
            @RequestParam int id) {
        Optional<TableExam1> t = tableExam1Repository.findById(id);
        tableExam1Repository.delete(t.get());
        // 위의 2줄 코드 1줄로 가능 : tableExam1Repository.deleteById(id);
        return "삭제 완료";
    }

    // PDF 4.28 ~ 34
    @Autowired
    ServiceCenterRepository serviceCenterRepository;

    @GetMapping("/sc/add")
    public ServiceCenter scAdd(
            @ModelAttribute ServiceCenter sc) {
        LocalDateTime localDateTime = LocalDateTime.now(); // . 찍고 바로 씀 : static (항상 메모리에 올라가있음)
        sc.setPurDate(localDateTime);
        Date date = new Date(); // new 할때 메모리에 올라감
        sc.setVstDate(date);
        ServiceCenter result = serviceCenterRepository.save(sc);
        return result;
    }
    @GetMapping("/sc/list")
    public List<ServiceCenter> scList() {
        List<ServiceCenter> result = serviceCenterRepository.findAll();
        return result;
    }
    @GetMapping("/sc/modify")
    public ServiceCenter scModify(
            @ModelAttribute ServiceCenter sc) {
        sc.setId(1);
        ServiceCenter result = serviceCenterRepository.save(sc);
        return result;
    }
    @GetMapping("/sc/delete")
    public String scRemove(
            @RequestParam Integer id) {
        serviceCenterRepository.deleteById(id);
        ;
        return "삭제 완료";
    }

    // hospital 조회
    @Autowired
    HospitalRepository hospitalRepository;

    @GetMapping("/hos/list")
    public List<Hospital> hosList() {
        return hospitalRepository.findAll();
    }

    // Team, Player 조회 (JSON)
    @Autowired
    TeamRepository teamRepository;
    @Autowired
    PlayerRepository playerRepository;

    @GetMapping("/team")
    public List<Team> team() {
        return teamRepository.findAll();
    }
    @GetMapping("/player")
    public List<Player> player() {
        return playerRepository.findAll();
    }
    // 실행하면 무한반복으로 출력됨 (서로 무한으로 부름) ===> Player entity에 @JsonIgnore 추가

}
