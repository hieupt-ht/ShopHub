package com.example.shophub;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordTest {
    @Test 
    void generatePassword(){
        System.out.println("hello");
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println("pass admin: " + passwordEncoder.encode("admin123"));
        System.out.println("pass user: " + passwordEncoder.encode("user123"));
    }
}
