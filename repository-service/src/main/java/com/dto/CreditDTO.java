package com.dto;

public class CreditDTO {
    private String creditName;
    private Integer creditId;

    public CreditDTO() {
    }

    public CreditDTO(String creditName, Integer creditId) {
        this.creditName = creditName;
        this.creditId = creditId;
    }

    public String getCreditName() {
        return creditName;
    }

    public Integer getCreditId() {
        return creditId;
    }
}