package com.example.basic.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.basic.model.Member;

@Controller
public class HtmlController {
    // @GetMapping("html/string") // 접속하는 주소
    // public String htmlString() {
    //     return "html/string"; // html 폴더 하위에 string 파일을 찾음
    // }
    // String 출력하는 방법
    @GetMapping("html/string")
    public String htmlString(Model model) {
        model.addAttribute("age", 35);
        return "html/string";
    }

    @GetMapping("html/void")
    public void htmlVoid() {
    }

    @GetMapping("html/map")
    public Map<String, Object> htmlMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("age", 30);
        map.put("address", "부산");
        return map;
    }

    @GetMapping("html/model")
    public Model htmlModel(Model model) {
        return model;
    }

    @GetMapping("html/model_and_view")
    public ModelAndView htmlModel() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("html/model_and_view");
        return mav;
    }

    // DTO (Date Transfer Object) : 데이터를 전달해주는 객체
    // VO (Value Object) 라는 표현을 쓰기도 함
    // Member import 안됨 -> Member 클래스 작성해야함
    @GetMapping("html/object")
    public Member htmlObject() {
        Member member = new Member();
        member.setName("kim");
        return member;
    }

}
