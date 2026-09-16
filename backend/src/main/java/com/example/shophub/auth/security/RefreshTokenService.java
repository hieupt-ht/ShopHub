package com.example.shophub.auth.security;

import java.time.Instant;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.example.shophub.auth.record.AuthResponse;
import com.example.shophub.auth.RefreshTokenRepository;
import com.example.shophub.refreshtoken.RefreshToken;
import com.example.shophub.user.User;
import com.example.shophub.auth.CustomUserDetailService;
import com.example.shophub.auth.security.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final CustomUserDetailService userDetailService; 
    private final JwtService jwtService;
    @Value("${refresh-token-expiration}")
    private Long expirationRefreshToken;

    public RefreshToken createRefreshToken(User user) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setRefreshtoken(UUID.randomUUID().toString());
        refreshToken.setUsers(user);
        refreshToken.setCreated_at(Instant.now());
        refreshToken.setExpires_at(Instant.now().plusMillis(expirationRefreshToken));
        refreshToken.setRevoked(false);
        refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    public RefreshToken verifyExpiration(RefreshToken refreshToken) {
        if (refreshToken.getExpires_at().isBefore(Instant.now())) {
            refreshTokenRepository.deteleByToken(refreshToken.getRefreshtoken());
            throw new RuntimeException("refresh token is expired");
        }
        return refreshToken;
    }
    public AuthResponse refreshToken(String requestRefreshToken){
        // lấy refeshtoken
        RefreshToken refreshToken = refreshTokenRepository.findByToken(requestRefreshToken)
        .map(token->verifyExpiration(token))
        .orElseThrow(()->new RuntimeException("Invalid refreshtoken"));
        // tìm userdeatail để xin cấp 1 token mới thông qua user
        User user = refreshToken.getUsers();
        UserDetails userDetails = userDetailService.loadUserByUsername(user.getUsername());
        String newToken = jwtService.generateAccessToken(userDetails);
        return new AuthResponse(newToken, requestRefreshToken);
    }
}
