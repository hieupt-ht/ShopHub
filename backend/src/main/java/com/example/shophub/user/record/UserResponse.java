package com.example.shophub.user.record;

public record UserResponse(
    int id,
    String username,
    String fullname,
    String email,
    String phone
) {
}
