package com.example.shophub.product;

import org.springframework.stereotype.Service;

import com.example.shophub.common.ObjectIsNotFoundException;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class ProductService implements IProductService {
    private final ProductRepository repository;
    @Override
    public Product getProductById(Long id) {
        return repository.findById(id).
        orElseThrow(()->new ObjectIsNotFoundException("product is not found"));
    }
}
