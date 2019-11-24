package com.dto;

public class CustomerDTO {
    private String firstName;
    private String surname;
    private String pesel;
    private Integer creditId;

    public CustomerDTO() {

    }

    public CustomerDTO(String firstName, String surname,
                       String pesel, Integer creditId) {
        this.firstName = firstName;
        this.surname = surname;
        this.pesel = pesel;
        this.creditId = creditId;
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
