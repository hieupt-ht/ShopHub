package com.example.shophub.user.mapper;

import org.mapstruct.Mapper;

import com.example.shophub.user.record.UserResponse;
import com.example.shophub.user.User;

@Mapper (componentModel = "spring")
public interface UserResponseMapper {
    UserResponse toUserResponse(User user);
}
