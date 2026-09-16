package com.example.shophub.auth.record;

public record AuthResponse(
    String accesstoken,
    String refreshToken
) {

}
