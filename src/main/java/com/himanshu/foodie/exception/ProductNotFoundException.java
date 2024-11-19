package com.himanshu.foodie.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class ProductNotFoundException extends RuntimeException {
    private final String message;

    public ProductNotFoundException(String message) {
        this.message = message;
    }
}
