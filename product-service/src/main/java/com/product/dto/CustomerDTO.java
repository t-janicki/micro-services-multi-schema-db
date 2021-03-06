package com.product.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CustomerDTO {
    @NotBlank
    private String firstName;
    @NotBlank
    private String surname;
    @NotBlank
    private String pesel;
    @NotNull
    private Integer creditId;

    public CustomerDTO() {

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

    public Integer getCreditId() {
        return creditId;
    }
}