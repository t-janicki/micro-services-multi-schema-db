package com.web.controller;

import com.domain.customer.Customer;
import com.dto.CustomerDTO;
import com.mapper.CustomerMapper;
import com.service.customer.CustomerServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/customer-db")
@Transactional
public class CustomerDBController {

    private CustomerServiceRepository serviceRepository;
    private CustomerMapper customerMapper;

    @Autowired
    public CustomerDBController(CustomerServiceRepository serviceRepository,
                                CustomerMapper customerMapper) {
        this.serviceRepository = serviceRepository;
        this.customerMapper = customerMapper;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createCustomer(@Valid @RequestBody CustomerDTO request) {

        serviceRepository.saveNewCustomer(request);

        return ResponseEntity.ok("Customer saved");
    }

    @GetMapping(value = "/{creditsIds}", produces = APPLICATION_JSON_VALUE)
    public List<CustomerDTO> getCustomersByCreditsIds(@PathVariable List<Integer> creditsIds) {

        List<Customer> customers = serviceRepository.getCustomersByCreditsIds(creditsIds);

        return customerMapper.mapToCustomerDTOList(customers);
    }
}
