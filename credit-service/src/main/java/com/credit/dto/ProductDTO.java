package com.credit.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProductDTO {
    @NotBlank
    private String productName;
    @NotNull
    private Integer value;
    @NotNull
    private Integer creditId;

    public ProductDTO() {
    }

    public String getProductName() {
        return productName;
    }

    public Integer getValue() {
        return value;
    }

    public Integer getCreditId() {
        return creditId;
    }
}