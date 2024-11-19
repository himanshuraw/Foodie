package com.himanshu.foodie.service;

import com.himanshu.foodie.dto.ProductRequest;
import com.himanshu.foodie.entity.Product;
import com.himanshu.foodie.mapper.ProductMapper;
import com.himanshu.foodie.repo.ProductRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductMapper productMapper;
    private final ProductRepo productRepo;

    public String createProduct(@Valid ProductRequest request) {
        Product product = productMapper.toEntity(request);
        productRepo.save(product);
        return "Product created";
    }
}
