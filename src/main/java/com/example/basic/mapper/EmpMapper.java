package com.example.basic.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmpMapper {
    // interface이기 때문에 메소드에 {} 없음
    public List<Map<String, Object>> select(String ename);

}
