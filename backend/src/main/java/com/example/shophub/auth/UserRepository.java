package com.example.shophub.auth;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.shophub.user.User;

public interface UserRepository extends JpaRepository<Long, User> {
    

}
