package com.example.shophub.refreshtoken;
import com.example.shophub.common.*;
import com.example.shophub.refreshtoken.record.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shophub.auth.security.RefreshTokenService;

import lombok.RequiredArgsConstructor;

@RestController 
@RequiredArgsConstructor 
@RequestMapping ("/api/v1")
public class RefreshTokenController {
    private final RefreshTokenService refreshTokenService;
    @GetMapping ("/refresh")
    public ApiResponse refreshToken(@RequestBody RefreshTokenRequest request){
        return new ApiResponse<>(200,
            "refresh token success",
            refreshTokenService.refreshToken(request.refreshtoken()));
    }
}
