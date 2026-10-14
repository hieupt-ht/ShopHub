package com.example.shophub.category;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.shophub.product.Product;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Getter 
@Setter 
@NoArgsConstructor
@Table (name = "categories")
public class Category {
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToMany (mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Product> products =ructor tường minh với đầy đủ các field new ArrayList<>();

    private String name;
    private String slug;
    private String description;
    private Date created_at;
    private Date updated_at;

    public Category(Long id, List<Product> products, String name, String slug,
            String description, Date created_at, Date updated_at) {
        this.id = id;
        this.products = products;
        this.name = name;
        this.slug = slug;
        this.description = description;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

}
