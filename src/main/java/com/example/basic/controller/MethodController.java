package com.example.basic.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MethodController {
    @PostMapping("req/post2")
    public String post() {
        return "POST";
    }
}
// API Tester 확장 프로그램
// : 브라우저에서 POST 방식 출력 안되니깐 어떻게 출력되는지 보려고 사용