package com.example.shophub.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shophub.common.ApiResponse;
import com.example.shophub.user.record.UserResponse;
import com.example.shophub.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController 
@RequiredArgsConstructor 
@RequestMapping ("/api/v1/user")
public class UserController {
    private final UserService userService;
    @GetMapping ("/me")
    public ApiResponse<UserResponse> getCurrentUser(){
        UserResponse userResponse = userService.getCurrentUser();
        return ApiResponse.success(userResponse);
    }
}
