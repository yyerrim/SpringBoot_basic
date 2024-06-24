package com.example.basic.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.basic.model.Emp;
import com.example.basic.model.Json;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class RequestController {
    @GetMapping("/referer")
    public String referer(
            @RequestHeader(value = "referer", required = false) String referer) {
        return referer + "";
    }
    // localhost:8080/referer => null 출력
    // localhost/ => 이동 누르면 localhost:8080/referer 페이지에 localhost:8080 출력

    @GetMapping("req/http")
    public String http(HttpServletRequest request) {
        String name = request.getParameter("name");
        String pageNum = request.getParameter("pageNum");
        return name + ", " + pageNum;
    } // http://localhost:8080/req/http?name=abc&pageNum=7

    @GetMapping("req/param1")
    public String param1(
            @RequestParam("key1") String key1,
            @RequestParam("key2") String key2) {
        return key1 + ", " + key2;
    } // http://localhost:8080/req/param1?key1=v1&key2=v2

    // req/emp : emp 테이블의 데이터를 JSON으로 출력
    // // + 5건씩 끊어서 첫번째 페이지의 데이터만 출력
    // @Autowired JdbcTemplate jt;
    // @GetMapping("req/emp")
    // public List<Map<String, Object>> reqEmp(
    // @RequestParam("page") String page) {
    // String sql = "select * from emp" + " limit 0, 5"; // 0 ~ 4번 데이터까지 출력
    // return jt.queryForList(sql);
    // } // http://localhost:8080/req/emp?page=1

    // // + 5건씩 끊어서 출력
    // @Autowired JdbcTemplate jt;
    // @GetMapping("req/emp")
    // public List<Map<String, Object>> reqEmp(
    // @RequestParam("page") String page) {
    // String sql = "";
    // if (page.equals("1")) {
    // sql = "select * from emp" + " limit 0, 5";
    // } else if (page.equals("2")) {
    // sql = "select * from emp" + " limit 5, 5";
    // } else if (page.equals("3")) {
    // sql = "select * from emp" + " limit 10, 5";
    // }
    // return jt.queryForList(sql);
    // }
    // // 규리&강사님 풀이
    // @Autowired JdbcTemplate jt;
    // @GetMapping("req/emp")
    // public List<Map<String, Object>> reqEmp(
    // @RequestParam("page") String page) {
    // int pageNum = Integer.parseInt(page) * 5 - 5; // 0번부터 시작하니깐 -4 가 아니라 -5
    // String sql = "select * from emp" + " limit " + pageNum + ", 5";
    // return jt.queryForList(sql);
    // }

    @Autowired JdbcTemplate jt;
    @GetMapping("req/emp")
    public List<Map<String, Object>> reqEmp(
            // 뒤에 defaultValue 등 다른거 들어가면 value 지정해줘야함
            @RequestParam(value = "page", defaultValue = "1", required = false) String page,
            @RequestParam(value = "ename", defaultValue = "", required = false) String ename) {
        // http://localhost:8080/req/emp?page=1 : ename = null 로 검색되기 때문에 아무것도 출력 안됨
        // http://localhost:8080/req/emp?page=1&ename= : 공백으로 검색되기 때문에 모든 자료 출력됨
        // 버그 해결 > defaultValue = "" 추가 : like ~ 는 ""일때 모든 자료 출력
        int pageNum = Integer.parseInt(page) * 5 - 5;
        String sql = "select * from emp"
                // + " where ename like '%ename%'"
                // + " where ename like concat('%', 'ename', '%')"
                + " where ename like concat('%', '" + ename + "', '%')"
                + " limit " + pageNum + ", 5";
        return jt.queryForList(sql);
    }

    // @RequestParam은 데이터 1건을 처리하지만, map을 사용하면 key:value, key:value, ... 로 처리
    // @GetMapping("req/param2")
    // public String param2(@RequestParam Map<String, Object> map) {
    // return map.toString();
    // } // http://localhost:8080/req/param2?search=java&version=1.8

    // key와 value를 나누어서 출력하는 코드 작성
    @GetMapping("req/param2")
    public String param2(@RequestParam Map<String, Object> map) {
        Set<String> keys = map.keySet(); // map에 있는 모든 key들을 뽑아냄 = map.entrySet();
        // set : 컬렉션프레임워크
        Iterator<String> iter = keys.iterator();
        // iterator : 반복자 : next() 메소드 호출하면 다음 요소가 나옴(다음 요소가 없을때까지)
        while (iter.hasNext()) {
            String key = iter.next();
            String value = (String) map.get(key);
        }
        return map.toString();
    }

    @GetMapping("req/path/{path1}/{path2}")
    public String path(
            @PathVariable("path1") String path1,
            @PathVariable("path2") String path2) {
        return path1 + ", " + path2;
    } // http://localhost:8080/req/path/123/456 , http://localhost:8080/req/path/language/java

    // @GetMapping("req/emp")를 @ModelAttribute로 수정해보기
    @GetMapping("req/emp2")
    public List<Map<String, Object>> reqEmp2(
            // @ModelAttribute("이름") DTO클래스
            @ModelAttribute("emp") Emp emp) {
        int pageNum = Integer.parseInt(emp.getPage()) * 5 - 5;
        String sql = "select * from emp"
                + " where ename like concat('%', '" + emp.getEname() + "', '%')"
                + " limit " + pageNum + ", 5";
        return jt.queryForList(sql);
    }

    // // PDF 3.34 연습문제
    // @GetMapping("reg/data")
    // // RequestController.java는 @RestController이기 때문에 @ResponseBody 없어도 실행됨
    // public Map<String, String> reqData(
    //     @RequestParam Map<String, String> map
    // ) {
    //     return map;
    // }

    // 프런트 url : localhost:5500/request.html / 백 url : localhost:8080/req/OOO
    // ===> 다른 서버라고 인식
    // 그냥 브라우저에서 요청을 보낼때는 아무 문제 없음
    // 하지만 자바스크립트를 통해서 요청을 보내게 되면 다른 서버일때 접근 거부함
    // 이때 백 서버는 접근을 허용해주기 위한 코드를 작성해줘야함
    // ===> @CrossOrigin : GET에 대해서 자바스크립트/브라우저에서의 모든 요청을 허용해주는 동작을 하게됨
    @CrossOrigin
    // resources - static 에 request.html 파일 넣어두면 @CrossOrigin 없어도 오류 안뜸
    // static 폴더안에 들어가있으면 spring에서 live server처럼 열 수 있게됨
    @GetMapping("req/get")
    public Map<String, String> reqGet(
            @RequestParam Map<String, String> map) {
        return map;
    }

    @CrossOrigin
    @PostMapping("req/post")
    public Map<String, String> reqPost(
            @RequestParam Map<String, String> map) {
        return map;
    }

    // 분산 서버 (js_request.html 활용한거)
    @CrossOrigin
    @PostMapping("req/json")
    // @ModelAttribute 사용하듯이 클래스 만들어줘야함 : Json.java
    public Json reqJson(
            @RequestBody Json json) {
        return json;
    }

}
