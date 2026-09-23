package com.example.shophub.product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shophub.common.ApiResponse;

import lombok.RequiredArgsConstructor;

@RestController 
@RequiredArgsConstructor 
@RequestMapping ("api/v1/product")
public class ProductController {
    private final IProductService productService;
    @GetMapping ("{id}")
    public ApiResponse getProductById(@PathVariable Long id){
        Product product = productService.getProductById(id);
        return new ApiResponse<>(200, "success", product);
    }
}
