package com.credit.web.request;

public class CreditRequest {
    private String firstName;
    private String surname;
    private String pesel;
    private String productName;
    private Integer productValue;
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
