package com.example.shophub.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.shophub.user.User;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class CustomUserDetailService
implements UserDetailsService
{
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByName(username)
        .orElseThrow(()-> new UsernameNotFoundException("user is not found"));
        return org.springframework.security.core.userdetails.User
        .withUsername(username)
        .password(user.getPassword())
        .roles(user.getRole())
        .build();
    }

}
