package com.credit.dto;

public class CustomerDTO {
    private String firstName;
    private String surname;
    private String pesel;
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
