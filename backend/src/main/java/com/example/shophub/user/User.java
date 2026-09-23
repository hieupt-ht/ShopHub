package com.example.shophub.user;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.shophub.refreshtoken.RefreshToken;
import com.example.shophub.order.Order;
import com.example.shophub.cart.*;
import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Table (name = "users")
@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor 
public class User {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (name = "username")
    private String username;
    private String email;
    private String password;
    @Column (name = "full_name")
    private String fullname;
    private String phone;
    private String role;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    @OneToOne (mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    private Cart cart;

    @OneToMany (mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    private List<RefreshToken> refreshTokens = new ArrayList<>();

    @OneToMany (mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();

}
