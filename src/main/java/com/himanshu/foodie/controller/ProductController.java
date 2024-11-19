package com.himanshu.foodie.controller;

import com.himanshu.foodie.dto.ProductRequest;
import com.himanshu.foodie.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody @Valid ProductRequest request) {
        return ResponseEntity.ok(productService.createProduct((request)));
    }

    @PutMapping("/{name}")
    public ResponseEntity<String> updateProduct(@PathVariable("name") String name, @RequestBody ProductRequest request) {
        return ResponseEntity.ok(productService.updateProduct(name, request));
    }
}
