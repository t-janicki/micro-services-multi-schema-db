package com.product.dto;

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

    public ProductDTO(String productName, Integer value, Integer creditId) {
        this.productName = productName;
        this.value = value;
        this.creditId = creditId;
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
