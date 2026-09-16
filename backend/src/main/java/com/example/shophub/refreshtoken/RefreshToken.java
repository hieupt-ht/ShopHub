package com.example.shophub.refreshtoken;

import java.time.Instant;
import java.util.Date;

import com.example.shophub.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Table (name = "refresh_tokens")
@Getter 
@Setter 
@AllArgsConstructor 
@NoArgsConstructor 
public class RefreshToken {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String refreshtoken;
    Instant expires_at;
    boolean revoked;
    Instant created_at;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "user_id")
    private User users;
}
