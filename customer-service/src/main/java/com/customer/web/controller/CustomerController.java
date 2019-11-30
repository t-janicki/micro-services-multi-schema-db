package com.customer.web.controller;

import com.customer.domain.Customer;
import com.customer.dto.CustomerDTO;
import com.customer.mapper.CustomerMapper;
import com.customer.service.CustomerService;
import com.customer.web.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

    private CustomerService customerService;
    private CustomerMapper customerMapper;

    @Autowired
    public CustomerController(CustomerService customerService,
                              CustomerMapper customerMapper) {
        this.customerService = customerService;
        this.customerMapper = customerMapper;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> createCustomer(@Valid @RequestBody CustomerDTO request) {

        customerService.createCustomer(request);

        return ResponseEntity.ok(new ApiResponse(200, "Customer created."));
    }

    @GetMapping(value = "/{creditsIds}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CustomerDTO>> getCustomersByCreditsIds(@PathVariable List<Integer> creditsIds) {
        List<Customer> customers = customerService.getCustomersByCreditsIds(creditsIds);

        return ResponseEntity.ok(customerMapper.mapToCustomerDTOList(customers));
    }
}
