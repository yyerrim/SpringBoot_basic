package com.example.basic.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.basic.entity.Emp;
import com.example.basic.entity.Hospital;
import com.example.basic.repository.EmpRepository;
import com.example.basic.repository.HospitalRepository;

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

    @GetMapping("/hospitalList")
    public String hospitalList(Model model) {
        List<Hospital> hospitalList = hospitalRepository.findAll();
        model.addAttribute("hospitalList", hospitalList);
        return "hospitalList";
    }

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

}
