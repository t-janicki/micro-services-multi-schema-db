package com.dto;

public class ProductDTO {
    private String productName;
    private Integer value;
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
