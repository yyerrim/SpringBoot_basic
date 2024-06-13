// Data Access Layer
package com.example.basic.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DemoDao {
    @Autowired
    JdbcTemplate jt;

    public List<Map<String, Object>> select() {
        return jt.queryForList("select * from demo");
    }

}
