package com.example.shophub.product;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shophub.common.ApiResponse;

import lombok.RequiredArgsConstructor;

@RestController 
@RequiredArgsConstructor 
@RequestMapping ("/v1/api/product")
public class ProductController {
    private final IProductService productService;
    public ApiResponse getProductById(@RequestBody Long id){
        Product product = productService.getProductById(id);
        return new ApiResponse<>(200, "success", product);
    }
}
