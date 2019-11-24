package com.credit.web.response;

public class CreditsDetailedResponse {
    private CustomerResponse customer;
    private ProductResponse product;
    private CreditNameResponse credit;

    public CreditsDetailedResponse() {
    }

    public CreditsDetailedResponse(CustomerResponse customer,
                                   ProductResponse product,
                                   CreditNameResponse credit) {
        this.customer = customer;
        this.product = product;
        this.credit = credit;
    }

    public CustomerResponse getCustomer() {
        return customer;
    }

    public ProductResponse getProduct() {
        return product;
    }

    public CreditNameResponse getCredit() {
        return credit;
    }
}
