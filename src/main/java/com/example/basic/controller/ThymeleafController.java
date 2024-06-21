package com.example.basic.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.basic.entity.Emp;
import com.example.basic.entity.Hospital;
import com.example.basic.repository.EmpRepository;
import com.example.basic.repository.HospitalRepository;
import com.example.basic.service.HospitalService;

@Controller
public class ThymeleafController {
    @Autowired
    EmpRepository empRepository;

    @GetMapping("/welcome")
    public String welcome(Model model) {
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        Map<String, Object> map = new HashMap<>();
        map.put("k1", "값1");
        map.put("k2", "값2");
        model.addAttribute("list", list);
        model.addAttribute("map", map);

        List<Emp> eList = empRepository.findAll();
        model.addAttribute("eList", eList);

        return "welcome";
    }

    @GetMapping("/user") // @GetMapping("user")도 가능하지만 / 넣어주는게 좋음
    public String user(Model model) {
        Map<String, Object> user = null;
        user = new HashMap<>();
        user.put("userId", "z");
        user.put("userName", "zoo");
        user.put("userAge", 25);
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/userList")
    public String userList(Model model) {
        List<Map<String, Object>> userList = new ArrayList<>();
        Map<String, Object> user = null;
        user = new HashMap<>();
        user.put("userId", "a");
        user.put("userName", "apple");
        user.put("userAge", 21);
        userList.add(user);
        user = new HashMap<>();
        user.put("userId", "b");
        user.put("userName", "banana");
        user.put("userAge", 22);
        userList.add(user);
        user = new HashMap<>();
        user.put("userId", "c");
        user.put("userName", "carrot");
        user.put("userAge", 23);
        userList.add(user);
        model.addAttribute("userList", userList);

        // emp 테이블 데이터 조회하기 : empno와 ename을 HTML의 Table로 출력하기 (th:each 반복문 사용)
        List<Emp> empList = empRepository.findAll();
        model.addAttribute("empList", empList);

        return "userList";
    }

    @Autowired
    HospitalRepository hospitalRepository;

    // @GetMapping("/hospitalList")
    // public String hospitalList(Model model) {
    //     List<Hospital> hospitalList = hospitalRepository.findAll();
    //     model.addAttribute("hospitalList", hospitalList);
    //     return "hospitalList";
    // }

    @GetMapping("/mode")
    public String mode(
            Model model, @RequestParam Map<String, Object> map) { // ?로 넘겨주는 데이터를 받음
        model.addAttribute("name", map.get("name"));
        model.addAttribute("auth", map.get("auth"));
        model.addAttribute("category", map.get("category"));

        model.addAttribute("now", map.get("now") == null ? "am" : map.get("now"));
        // 사용자가 입력하지 않으면 기본값으로 "am"
        return "mode";
    }

    @GetMapping("/pagination")
    public String pagination(
            Model model, @RequestParam(defaultValue = "1") int page) {
        int startPage = (page - 1) / 10 * 10 + 1;
        int endPage = startPage + 9;
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("page", page);
        return "pagination";
    }

    @GetMapping("/layout1")
    public String layout1() {
        return "layout1";
    }
    @GetMapping("/layout2")
    public String layout2() {
        return "layout2";
    }

    @GetMapping("/insert1")
    public String insert1() {
        return "insert1";
    }

    // 연습) Pasing, 정렬
    // 1. 한 페이지에 데이터를 10개씩 보여주기
    // 2. 시군구 오름차순 정렬 - 시군구가 같다면 병원명 내림차순
    @GetMapping("/hospitalList")
    public List<Hospital> hospitalList(
            @RequestParam(defaultValue = "1") int page) {
        Sort sort = Sort.by(Sort.Order.asc("sido"), Sort.Order.desc("name"));
        Pageable pageable = PageRequest.of(page - 1, 10, sort);
        Page<Hospital> p = hospitalRepository.findAll(pageable);
        List<Hospital> list = p.getContent();
        return list;
    }

    // 강사님 풀이
    // ===> HospitalService.java
    // 서비스로 코드를 옮기는것에 기준은 없음(모든 코드가 들어가지는 않아도됨)
    @Autowired
    HospitalService hospitalService;

    @GetMapping("/html/hospital")
    public String hospital(Model model,
            @RequestParam(defaultValue = "1") int page) {
        int startPage = (page - 1) / 10 * 10 + 1;
        int endPage = startPage + 9;
        // Order ord1 = Order.asc("sido");
        // Order ord2 = Order.desc("name");
        // Sort sort = Sort.by(ord1, ord2);
        // Pageable pageable = PageRequest.of(page - 1, 10, sort);
        // Page<Hospital> p = hospitalRepository.findAll(pageable);
        Page<Hospital> p = hospitalService.getList(page);

        int totalPage = p.getTotalPages();
        if (endPage > totalPage) {
            endPage = totalPage;
        }

        model.addAttribute("hospitalList", p.getContent());
        // hospitalList.html에서 "hos : ${hospitalList}"와 이름 같아야함

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("totalPage", totalPage);
        return "hospitalList";
    }
}
