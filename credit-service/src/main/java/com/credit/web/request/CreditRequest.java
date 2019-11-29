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

    public CreditRequest(String firstName, String surname, String pesel,
                         String productName, Integer productValue, String creditName) {
        this.firstName = firstName;
        this.surname = surname;
        this.pesel = pesel;
        this.productName = productName;
        this.productValue = productValue;
        this.creditName = creditName;
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
