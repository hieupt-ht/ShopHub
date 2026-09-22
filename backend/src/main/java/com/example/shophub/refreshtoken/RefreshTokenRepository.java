package com.example.shophub.refreshtoken;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository 
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long>{
    Optional<RefreshToken> findByRefreshtoken(String token);
    void deleteByRefreshtoken(String token);
}
