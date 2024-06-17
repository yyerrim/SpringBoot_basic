package com.example.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.basic.entity.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {

}
