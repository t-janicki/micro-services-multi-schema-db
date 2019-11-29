package com.customer.service;

import com.customer.domain.Customer;
import com.customer.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {

    Customer createCustomer(CustomerDTO customerDTO);

    List<Customer> getCustomersByCreditsIds(List<Integer> creditsIds);
}
