package com.example.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.basic.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
    // 아이디가 틀렸습니다. 비밀번호가 틀렸습니다. : 커스텀 메소드 안만들어도됨
    // 접속 정보를 확인해주세요. 아이디 또는 비밀번호를 확인해주세요. : 커스텀 메소드 만들어야함
    public User findByUserIdAndUserPw(String userId, String userPw);
}
