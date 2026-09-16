package com.example.shophub.auth;
import com.example.shophub.auth.*;
import com.example.shophub.auth.record.LoginRequest;
import com.example.shophub.auth.record.AuthResponse;
import com.example.shophub.common.*;
import com.example.shophub.auth.security.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping ("api/v1/auth")
@RequiredArgsConstructor 
public class AuthController {
    private final AuthService authService;
    @PostMapping ("/login")
    public ApiResponse login(@RequestBody LoginRequest request){
        return new ApiResponse<AuthResponse>(400, "login success", authService.login(request));
    }
}
