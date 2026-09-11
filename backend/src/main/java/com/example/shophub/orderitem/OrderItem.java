package com.example.shophub.orderitem;

import java.math.BigDecimal;

import com.example.shophub.order.*;
import com.example.shophub.product.*;
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
@Entity
@Getter 
@Setter 
@AllArgsConstructor 
@NoArgsConstructor 
@Table (name = "order_items")
public class OrderItem {
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "order_id")
    private Order order;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "product_id")
    private Product product;

    @Column (name = "product_name")
    private String productname;

    private BigDecimal price;
    private int quantity;
}
