package com.example.shophub.cart;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.shophub.cartitems.CartItems;
import com.example.shophub.user.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor 
@Table (name = "cart")
public class Cart {
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne  (fetch = FetchType.LAZY)
    @JoinColumn (name = "user_id", nullable = false, unique = true)
    private User user;

    @OneToMany (mappedBy = "cart", cascade = CascadeType.ALL)
    List<CartItems> cartItems = new ArrayList<>();

    private Date created_at;
    private Date updated_at;
}
