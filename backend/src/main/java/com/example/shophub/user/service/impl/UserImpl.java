package com.example.shophub.user.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.shophub.user.record.UserResponse;
import com.example.shophub.user.service.UserService;
import com.example.shophub.common.ObjectIsNotFoundException;
import com.example.shophub.user.mapper.*;
import com.example.shophub.user.*;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserImpl implements UserService{
    private final UserRepository userRepository;
    private final UserResponseMapper userMapper;
    @Override
    public UserResponse getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsername(username)
        .orElseThrow(()-> new ObjectIsNotFoundException("user is not found"));
        UserResponse userResponse = userMapper.toUserResponse(user);
        return userResponse;
    }
}
