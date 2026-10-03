package com.example.shophub.auth.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.example.shophub.auth.record.AuthResponse;
import com.example.shophub.auth.record.LoginRequest;
import com.example.shophub.auth.jwt.*;
import com.example.shophub.refreshtoken.*;
import com.example.shophub.user.*;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final UserRepository userRepository;
    public AuthResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));
        // nếu login thành công
        // lấy ra userdetail từ token
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // tạo accestoken
        String accessToken = jwtService.generateAccessToken(userDetails);
        // lấy user thông qua userdetails
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();
        // tạo refreshtoken
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user);
        return new AuthResponse(accessToken, refreshToken.getRefreshtoken());
    }
    public AuthResponse refresh(String refresh){
        return refreshTokenService.refreshToken(refresh);
    }
}
