package com.customer.web.controller;

import com.customer.domain.Customer;
import com.customer.dto.CustomerDTO;
import com.customer.mapper.CustomerMapper;
import com.customer.service.CustomerService;
import com.customer.web.response.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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
    private Environment environment;

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    public CustomerController(CustomerService customerService,
                              CustomerMapper customerMapper,
                              Environment environment) {
        this.customerService = customerService;
        this.customerMapper = customerMapper;
        this.environment = environment;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> createCustomer(@Valid @RequestBody CustomerDTO request) {

        customerService.createCustomer(request);

        LOGGER.info("Customer service port " + environment.getProperty("local.server.port"));

        return ResponseEntity.ok(new ApiResponse(200, "Customer created."));
    }

    @GetMapping(value = "/{creditsIds}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CustomerDTO>> getCustomersByCreditsIds(@PathVariable List<Integer> creditsIds) {
        List<Customer> customers = customerService.getCustomersByCreditsIds(creditsIds);

        LOGGER.info("Customer service port " + environment.getProperty("local.server.port"));

        return ResponseEntity.ok(customerMapper.mapToCustomerDTOList(customers));
    }
}
