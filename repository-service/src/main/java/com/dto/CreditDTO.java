package com.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreditDTO {
    @NotBlank
    private String creditName;
    @NotNull
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