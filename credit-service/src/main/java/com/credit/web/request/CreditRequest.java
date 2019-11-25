package com.credit.web.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreditRequest {
    @NotBlank
    private String firstName;
    @NotBlank
    private String surname;
    @NotNull
    private String pesel;
    @NotBlank
    private String productName;
    @NotNull
    private Integer productValue;
    @NotBlank
    private String creditName;

    public CreditRequest() {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public String getPesel() {
        return pesel;
    }

    public String getProductName() {
        return productName;
    }

    public Integer getProductValue() {
        return productValue;
    }

    public String getCreditName() {
        return creditName;
    }
}
