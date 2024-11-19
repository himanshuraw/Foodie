package com.himanshu.foodie.service;

import com.himanshu.foodie.dto.ProductRequest;
import com.himanshu.foodie.dto.ProductResponse;
import com.himanshu.foodie.entity.Product;
import com.himanshu.foodie.exception.ProductNotFoundException;
import com.himanshu.foodie.mapper.ProductMapper;
import com.himanshu.foodie.repo.ProductRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

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

    public ProductResponse retrieveProduct(Long id) {
        Product product = getProduct(id);
        return productMapper.toResponse(product);
    }

    public Product getProduct(Long id) {
        return productRepo.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(
                        format("No product with ProductID %d is found", id)
                ));
    }

    public String updateProduct(Long id, ProductRequest request) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        if (request.name() != null) {
            product.setName(request.name());
        }
        if (request.price() != null) {
            product.setPrice(request.price());
        }

        productRepo.save(product);
        return "Product details updated";
    }

    public String deleteProduct(Long id) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        String name = product.getName();
        productRepo.delete(product);
        return "Product " + name + " deleted";
    }
}
