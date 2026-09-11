package com.example.shophub.order;

import jakarta.persistence.Column;
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

import java.math.BigDecimal;
import java.util.Date;

import com.example.shophub.user.User;
@Entity
@Getter 
@Setter 
@AllArgsConstructor 
@NoArgsConstructor 
@Table (name = "orders")
public class Order {
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "user_id")
    private User user;
    @Column (name = "total_amount")
    private BigDecimal totalamount;
    private String status;
    @Column (name = "shipping_name")
    private String shippingname;

    @Column (name = "shipping_phone")
    private String phone;
    @Column (name = "shipping_address")
    private String shippingaddress;
    private Date created_at;
    private Date updated_at;
     
}
