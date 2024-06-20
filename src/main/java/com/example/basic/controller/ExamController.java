package com.example.basic.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.basic.entity.Dept;
import com.example.basic.entity.Emp;
import com.example.basic.entity.Major;
import com.example.basic.entity.Team;
import com.example.basic.repository.DeptRepository;
import com.example.basic.repository.EmpRepository;
import com.example.basic.repository.MajorRepository;

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

    // 연습 - 회원가입 기능 작성하기 : 단일 서버
    @GetMapping("req/signup")
    public String signup() {
        return "signup";
    }
    @PostMapping("req/signup")
    @ResponseBody
    public Map<String, Object> signupPost(
            @RequestBody Map<String, Object> map) {
        return map;
    }

    // PDF 4.35 연습문제
    @Autowired
    MajorRepository majorRepository;

    @GetMapping("/major/add")
    @ResponseBody
    public Major majorAdd(
            @ModelAttribute Major mj) {
            // @RequestParam으로 ?의 값들을 따로 꺼내서 major 객체를 만들어서 넣으려면 귀찮기 때문에 @ModelAttribute 사용
        Date date = new Date();
        mj.setEbtbDate(date);
        return majorRepository.save(mj);
    }
    @GetMapping("/major/list")
    @ResponseBody
    public List<Major> majorList(
            @ModelAttribute Major mj) {
        return majorRepository.findAll();
    }

    // Emp, Dept url로 JSON 결과 출력
    @Autowired
    DeptRepository deptRepository;
    @Autowired
    EmpRepository empRepository;

    @GetMapping("/dept")
    @ResponseBody
    public List<Dept> dept() {
        return deptRepository.findAll();
    }
    // @GetMapping("/emp")
    // @ResponseBody
    // public List<Emp> emp() {
    //     return empRepository.findAll();
    // }

    // // Pasing
    // @GetMapping("/emp")
    // @ResponseBody
    // public List<Emp> emp() {
    //     // 데이터 3개만 출력
    //     Pageable pageable = PageRequest.of(1, 3); //3개씩 끊어서 두번째 페이지 출력
    //     Page<Emp> p = empRepository.findAll(pageable);
    //     List<Emp> list = p.getContent();
    //     return list;
    // }
    // // /emp?page=1 ===> 3개씩 끊어서 첫번째 페이지 출력되도록 해보기
    // @GetMapping("/emp")
    // @ResponseBody
    // public List<Emp> emp(
    //         @RequestParam(defaultValue = "1") int page) {
    //     Pageable pageable = PageRequest.of(page - 1, 3);
    //     Page<Emp> p = empRepository.findAll(pageable);
    //     List<Emp> list = p.getContent();
    //     return list;
    // }

    // // 정렬
    // @GetMapping("/emp")
    // @ResponseBody
    // public List<Emp> emp(
    //         @RequestParam(defaultValue = "1") int page) {
    //     // Sort sort = new Sort(); // new 안될때는 static 방식으로
    //     Sort sort = Sort.by("sal"); // 급여 순 정렬
    //     Pageable pageable = PageRequest.of(page - 1, 3, sort);
    //     Page<Emp> p = empRepository.findAll(pageable);
    //     List<Emp> list = p.getContent();
    //     return list;
    // }

    // @GetMapping("/emp")
    // @ResponseBody
    // public Map<String, Object> emp(
    //         @RequestParam(defaultValue = "1") int page) {
    //     Map<String, Object> map = new HashMap<>();
    //     Sort sort = Sort.by("sal");
    //     // // 급여가 같다면 이름순으로
    //     // Sort sort = Sort.by("sal", "ename");
    //     Pageable pageable = PageRequest.of(page - 1, 3, sort);
    //     Page<Emp> p = empRepository.findAll(pageable);
    //     List<Emp> list = p.getContent();
    //     map.put("emp_list", list);
    //     map.put("total_page", p.getTotalPages());
    //     map.put("total_count", p.getTotalElements());
    //     map.put("page", page);
    //     return map;
    // }

    // // 사용자 정의 메소드(EmpRepository.java)를 사용해서 이름에 a 들어가는 사람, Pasing 출력
    // @GetMapping("/emp")
    // @ResponseBody
    // public Map<String, Object> emp(
    //         @RequestParam(defaultValue = "1") int page) {
    //     Map<String, Object> map = new HashMap<>();
    //     Sort sort = Sort.by("sal");
    //     Pageable pageable = PageRequest.of(page - 1, 3, sort);
    //     List<Emp> list = empRepository.findByEnameLike("%a%", pageable);
    //     map.put("emp_list", list);
    //     map.put("page", page);
    //     return map;
    // }

    // Sort 말고 사용자 정의 메소드 OrderBy 사용해보기
    @GetMapping("/emp")
    @ResponseBody
    public Map<String, Object> emp(
            @RequestParam(defaultValue = "1") int page) {
        Map<String, Object> map = new HashMap<>();
        Sort sort = Sort.by("sal");
        Pageable pageable = PageRequest.of(page - 1, 3, sort);
        List<Emp> list = empRepository.findByEnameLikeOrderByEnameAsc("%a%", pageable);
        map.put("emp_list", list);
        map.put("page", page);
        return map;
    }
    
}
