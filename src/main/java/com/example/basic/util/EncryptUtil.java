package com.example.basic.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;

// 비밀번호 암호화 : Bean으로 등록
@Component
public class EncryptUtil {
    public String encode(String raw) {
        MessageDigest md;
        String hex = "";
        try {
            md = MessageDigest.getInstance("SHA-256");
            md.update(raw.getBytes());
            hex = String.format("%064x", new BigInteger(1, md.digest()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hex;
    }
}
