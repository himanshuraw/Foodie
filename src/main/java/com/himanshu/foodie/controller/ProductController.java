package com.himanshu.foodie.controller;

import com.himanshu.foodie.dto.ProductRequest;
import com.himanshu.foodie.dto.ProductResponse;
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

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProducts(@PathVariable("id") Long id) {
        return ResponseEntity.ok(productService.retrieveProduct(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable("id") Long id, @RequestBody ProductRequest request) {
        return ResponseEntity.ok(productService.updateProduct(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long id) {
        return ResponseEntity.ok(productService.deleteProduct(id));
    }
}
