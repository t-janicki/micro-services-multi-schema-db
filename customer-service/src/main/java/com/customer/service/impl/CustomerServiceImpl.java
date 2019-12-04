package com.customer.service.impl;

import com.customer.domain.Customer;
import com.customer.dto.CustomerDTO;
import com.customer.repository.CustomerRepository;
import com.customer.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public Customer createCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setFirstName(customerDTO.getFirstName());
        customer.setSurname(customerDTO.getSurname());
        customer.setPesel(customerDTO.getPesel());
        customer.setCreditId(customerDTO.getCreditId());

        customerRepository.save(customer);

        LOGGER.info("New customer saved. ");
        return customer;
    }

    @Override
    public List<Customer> getCustomersByCreditsIds(List<Integer> creditsIds) {
        return customerRepository.findAllByCreditIdIn(creditsIds);
    }
}
