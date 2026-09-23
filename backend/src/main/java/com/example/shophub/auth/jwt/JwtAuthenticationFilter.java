package com.example.shophub.auth.jwt;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;
import com.example.shophub.auth.*;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final CustomUserDetailService userDetailService;
    @Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
                // lấy authorization header
                String authHeader = request.getHeader("Authorization");
                // Nếu không có Bearer token, cho request đi tiếp.
                if (authHeader == null || !authHeader.startsWith("Bearer")) {
                    filterChain.doFilter(request, response);
                    return;
                }
                // lấy ra token
                String accessToken = authHeader.substring(7);
                // extract token
                try {
                    String username = jwtService.extractUsername(accessToken);
                    // kiểm tra token có hợp lệ và còn hạn hay không
                    UserDetails userDetails = userDetailService.loadUserByUsername(username);
                    if(jwtService.isTokenValid(accessToken, userDetails)){
                        // nếu token hợp lệ -> tạo authentication
                        UsernamePasswordAuthenticationToken authentication =
                         new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        // đưa authentication vào securitycontex quản lý
                        SecurityContextHolder.
                        getContext().
                        setAuthentication(authentication);
                    }
                } catch (Exception e) {
                }
                // cho request đi tiếp
                filterChain.doFilter(request, response);
	}

}
