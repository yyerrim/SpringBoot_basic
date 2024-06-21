package com.example.basic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.example.basic.entity.Hospital;
import com.example.basic.repository.HospitalRepository;

@Service
public class HospitalService {
    @Autowired
    HospitalRepository hospitalRepository;

    // ThymeleafController.java : @GetMapping("/html/hospital")
    public Page<Hospital> getList(int page) {
        Order ord1 = Order.asc("sido");
        Order ord2 = Order.desc("name");
        Sort sort = Sort.by(ord1, ord2);
        Pageable pageable = PageRequest.of(page - 1, 10, sort);
        Page<Hospital> p = hospitalRepository.findAll(pageable);
        return p;
    }

    // DBController.java : @GetMapping("/hos/list")
    public List<Hospital> getJson() {
        return hospitalRepository.findAll();
    }
}
