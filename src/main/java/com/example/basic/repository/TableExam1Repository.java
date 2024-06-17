package com.example.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.basic.entity.TableExam1;

// interface끼리는 extends로 상속받음
// JpaRepository<활용하는 entity, primary key>
public interface TableExam1Repository extends JpaRepository<TableExam1, Integer> {
    // @Autowired 하기 위해서 repository 만드는거
    // Bean으로 등록됐기 때문에 @Autowired로 불러올 수 있음
}
