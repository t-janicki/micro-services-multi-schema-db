package com.customer.service;

import com.customer.dto.CustomerDTO;
import com.customer.web.response.ApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface CustomerService {

    ApiResponse createCustomer(CustomerDTO customerDTO) throws JsonProcessingException;

    List<CustomerDTO> getCustomersByCreditsIds(List<Integer> creditsIds);
}
