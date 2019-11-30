package com.customer.mapper;

import com.customer.domain.Customer;
import com.customer.dto.CustomerDTO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CustomerMapperTest {

    @InjectMocks
    private CustomerMapper customerMapper;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void mapToCustomerDTO() {
        //GIVEN
        Customer customer = new Customer();

        //WHEN
        CustomerDTO customerDTO = customerMapper.mapToCustomerDTO(customer);

        //THEN
        assertNotSame(customer, customerDTO);
    }

    @Test
    public void mapToCustomerDTOList() {
        //GIVEN
        List<Customer> customers = new ArrayList<>();

        //WHEN
        List<CustomerDTO> customerDTOList = customerMapper.mapToCustomerDTOList(customers);

        //THEN
        assertNotSame(customers, customerDTOList);
    }
}