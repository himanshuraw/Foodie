package com.himanshu.foodie.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ProductResponse(
        @JsonProperty("id")
        Long id,
        @JsonProperty("name")
        String name,
        @JsonProperty("price")
        Double price
) {
}
