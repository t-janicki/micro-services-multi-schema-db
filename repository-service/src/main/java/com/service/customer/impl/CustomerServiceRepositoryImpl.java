package com.service.customer.impl;

import com.domain.customer.Customer;
import com.dto.CustomerDTO;
import com.repository.customer.CustomerRepository;
import com.service.customer.CustomerServiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerServiceRepositoryImpl implements CustomerServiceRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceRepositoryImpl.class);
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceRepositoryImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public Customer saveNewCustomer(CustomerDTO customerDTO) {
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
