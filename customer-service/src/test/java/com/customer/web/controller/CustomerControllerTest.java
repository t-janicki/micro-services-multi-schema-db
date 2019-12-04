package com.customer.web.controller;

import com.customer.domain.Customer;
import com.customer.dto.CustomerDTO;
import com.customer.mapper.CustomerMapper;
import com.customer.service.CustomerService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @MockBean
    private CustomerMapper customerMapper;

    @Test
    public void shouldFetchCreateCustomer() throws Exception {
        //GIVEN
        CustomerDTO customerDTO = new CustomerDTO("First name", "Surname", "99484833123", 5512384);

        Gson gson = new Gson();
        String json = gson.toJson(customerDTO);

        //WHEN & THEN
        mockMvc.perform(post("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("Customer created.")))
                .andExpect(jsonPath("$.status", is(200)));
    }

    @Test
    public void shouldFetchGetCustomersByCreditsIds() throws Exception {
        //GIVEN
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        customerDTOList.add(new CustomerDTO("First name 1", "Surname 1", "99484833123", 23468));
        customerDTOList.add(new CustomerDTO("First name 2", "Surname 2", "99484833123", 9588918));
        customerDTOList.add(new CustomerDTO("First name 3", "Surname 3", "99484833123", 50452));
        customerDTOList.add(new CustomerDTO("First name 4", "Surname 4", "99484833123", 4235));

        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer("First name 1", "Surname 1", "99484833123", 23468));
        customers.add(new Customer("First name 2", "Surname 2", "99484833123", 9588918));
        customers.add(new Customer("First name 3", "Surname 3", "99484833123", 50452));
        customers.add(new Customer("First name 4", "Surname 4", "99484833123", 4235));

        when(customerService.getCustomersByCreditsIds(Arrays.asList(23468, 9588918, 50452))).thenReturn(customers);
        when(customerMapper.mapToCustomerDTOList(customers)).thenReturn(customerDTOList);

        //WHEN & THEN
        mockMvc.perform(get("/customers/23468,9588918,50452")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))
                .andReturn();
    }

    @Test
    public void shouldFetchEmptyCustomersList() throws Exception {
        //GIVEN
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        List<Customer> customers = new ArrayList<>();

        when(customerService.getCustomersByCreditsIds(Arrays.asList(23468, 9588918, 50452))).thenReturn(customers);
        when(customerMapper.mapToCustomerDTOList(customers)).thenReturn(customerDTOList);

        //WHEN & THEN
        mockMvc.perform(get("/customers/23468,9588918,50452")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)))
                .andReturn();
    }
}