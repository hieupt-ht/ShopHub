package com.example.shophub.product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.shophub.cartitems.*;
import com.example.shophub.category.Category;
import com.example.shophub.images.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table (name = "products")
public class Product {
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany (mappedBy = "product", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Image> images = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "category_id")
    private Category category;
    

    private String name;
    private String slug;
    private String description;
    private BigDecimal price;
    private int stock;
    private String status;
    private Date created_at;
    private Date updated_at;
}
