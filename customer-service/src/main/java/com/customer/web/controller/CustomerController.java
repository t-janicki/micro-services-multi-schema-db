package com.customer.web.controller;

import com.customer.dto.CustomerDTO;
import com.customer.service.CustomerService;
import com.customer.web.response.ApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> createCustomer(@RequestBody CustomerDTO request) throws JsonProcessingException {

        ApiResponse response = customerService.createCustomer(request);

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{creditsIds}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CustomerDTO>> getCustomersByCreditsIds(@PathVariable List<Integer> creditsIds) {
        return ResponseEntity.ok(customerService.getCustomersByCreditsIds(creditsIds));
    }
}
