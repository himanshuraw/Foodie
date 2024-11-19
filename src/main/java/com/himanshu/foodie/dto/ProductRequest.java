package com.himanshu.foodie.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ProductRequest(
        @NotNull(message = "Product name should be present")
        @NotBlank(message = "Product name should be present")
        @NotEmpty(message = "Product name should be present")
        @JsonProperty("name")
        String name,

        @NotNull(message = "Price should be present")
        @JsonProperty("price")
        Double price
) {
}
