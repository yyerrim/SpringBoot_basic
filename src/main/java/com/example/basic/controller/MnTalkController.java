package com.example.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MnTalkController {
    @GetMapping("/mntalk")
    public String mntalk() {
        return "mntalk/index";
    }
}
