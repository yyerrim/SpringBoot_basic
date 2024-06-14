package com.example.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.basic.entity.ServiceCenter;

public interface ServiceCenterRepository extends JpaRepository<ServiceCenter, Integer> {
    
}
