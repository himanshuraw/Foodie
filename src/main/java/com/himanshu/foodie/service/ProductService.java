package com.himanshu.foodie.service;

import com.himanshu.foodie.dto.ProductRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    public String createProduct(@Valid ProductRequest request) {
        return "Customer Created";
    }
}
