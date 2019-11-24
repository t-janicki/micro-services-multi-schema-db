package com.credit.web.response;

public class CustomerResponse {
    private String firstName;
    private String surname;
    private String pesel;

    public CustomerResponse() {

    }

    public CustomerResponse(String firstName, String surname, String pesel) {
        this.firstName = firstName;
        this.surname = surname;
        this.pesel = pesel;
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
}
