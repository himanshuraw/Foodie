package com.himanshu.foodie.mapper;

import com.himanshu.foodie.dto.ProductRequest;
import com.himanshu.foodie.dto.ProductResponse;
import com.himanshu.foodie.entity.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    public Product toEntity(ProductRequest request) {
        return Product.builder()
                .name(request.name())
                .price(request.price())
                .build();
    }

    public ProductResponse toResponse(Product product) {
        return new ProductResponse(product.getId(), product.getName(), product.getPrice());
    }
}
