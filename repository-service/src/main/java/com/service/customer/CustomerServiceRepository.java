package com.service.customer;

import com.domain.customer.Customer;
import com.dto.CustomerDTO;

import java.util.List;

public interface CustomerServiceRepository {

    Customer saveNewCustomer(CustomerDTO customerDTO);

    List<Customer> getCustomersByCreditsIds(List<Integer> creditsIds);
}
