package com.credit.web.response;

public class ProductResponse {
    private String productName;
    private Integer productValue;

    public ProductResponse() {

    }

    public ProductResponse(String productName, Integer productValue) {
        this.productName = productName;
        this.productValue = productValue;
    }

    public String getProductName() {
        return productName;
    }

    public Integer getProductValue() {
        return productValue;
    }
}
