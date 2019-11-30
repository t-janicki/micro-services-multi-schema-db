package com.customer.service;

import com.customer.domain.Customer;
import com.customer.dto.CustomerDTO;
import com.customer.repository.CustomerRepository;
import com.customer.service.impl.CustomerServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CustomerServiceTest {

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldFetchCreateCustomer() {
        //GIVEN
        CustomerDTO customerDTO = new CustomerDTO("First name", "Surname", "85721847211", 8472174);

        Customer customer = new Customer();
        customer.setFirstName(customerDTO.getFirstName());
        customer.setSurname(customerDTO.getSurname());
        customer.setPesel(customerDTO.getPesel());
        customer.setCreditId(customerDTO.getCreditId());

        //WHEN
        Customer result = customerService.createCustomer(customerDTO);

        //THEN
        assertEquals("First name", result.getFirstName());
        assertEquals("Surname", result.getSurname());
        assertEquals("85721847211", result.getPesel());
        assertEquals(8472174, result.getCreditId().intValue());
    }

    @Test
    public void shouldFetchGetCustomersByCreditsIds() {
        //GIVEN
        Customer customer1 = new Customer("First name 1", "Surname 1", "85721847211", 8472171);
        Customer customer2 = new Customer("First name 2", "Surname 2", "85721847212", 8472172);
        Customer customer3 = new Customer("First name 3", "Surname 3", "85721847213", 8472173);

        List<Customer> customers = new ArrayList<>();
        customers.add(customer1);
        customers.add(customer2);
        customers.add(customer3);

        List<Integer> creditsIds = Arrays.asList(8472171, 8472172, 8472173);

        when(customerRepository.findAllByCreditIdIn(creditsIds)).thenReturn(customers);

        //WHEN
        List<Customer> result = customerService.getCustomersByCreditsIds(creditsIds);

        //THEN
        assertEquals(customers.size(), result.size());
        verify(customerRepository, times(1)).findAllByCreditIdIn(creditsIds);
    }
}
