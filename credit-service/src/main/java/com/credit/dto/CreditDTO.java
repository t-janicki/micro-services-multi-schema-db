package com.credit.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreditDTO {
    @NotBlank
    private String creditName;
    @NotNull
    private Integer creditId;

    public CreditDTO() {

    }

    public String getCreditName() {
        return creditName;
    }

    public Integer getCreditId() {
        return creditId;
    }
}
