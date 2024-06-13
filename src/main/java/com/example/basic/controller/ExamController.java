package com.example.basic.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ExamController {

    // PDF 3.15 연습문제 - 1
    @GetMapping("html/exam")
    public String htmlExam() {
        return "html/exam";
    }

    // PDF 3.17 연습문제 - 2
    @GetMapping("json/exam")
    @ResponseBody // @Controller 클래스에서 사용할때 써줘야함
    public Map<String, Object> jsonExam() {
        Map<String, Object> map = new HashMap<>();
        map.put("count", 2);

        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map2 = new HashMap<>();
        map2.put("name", "가");
        map2.put("userId", "A");
        map2.put("userPassword", "1");
        list.add(map2);

        Map<String, Object> map3 = new HashMap<>();
        map3.put("name", "나");
        map3.put("userId", "B");
        map3.put("userPassword", "2");
        list.add(map3);

        map.put("list", list);
        return map;
    }

    // PDF 3.34 연습문제
    @GetMapping("reg/data")
    @ResponseBody // ExamController.java는 @Controller이기 때문에 JSON 실행하려면 @ResponseBody 추가해줘야함
    public Map<String, String> reqData(
        @RequestParam Map<String, String> map
    ) {
        return map;
    }

}
