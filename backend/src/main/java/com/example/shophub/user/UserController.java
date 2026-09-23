package com.example.shophub.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shophub.common.ApiResponse;

import lombok.RequiredArgsConstructor;

@RestController 
@RequiredArgsConstructor 
@RequestMapping ("/api/v1/user")
public class UserController {
    @GetMapping 
    public ApiResponse test(){
        return new ApiResponse<>(400, "hello", null);
    }
}
