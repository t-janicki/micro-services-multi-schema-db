package com.customer.service.impl;

import com.customer.dto.CustomerDTO;
import com.customer.service.CustomerService;
import com.customer.web.response.ApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Override
    public ApiResponse createCustomer(CustomerDTO customerDTO) throws JsonProcessingException {

        Map<String, String> customerInfo = new HashMap<>();
        customerInfo.put("firstName", customerDTO.getFirstName());
        customerInfo.put("surname", customerDTO.getSurname());
        customerInfo.put("pesel", customerDTO.getPesel());
        customerInfo.put("creditId", Integer.toString(customerDTO.getCreditId()));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String json = new ObjectMapper().writeValueAsString(customerInfo);
        HttpEntity<String> entity = new HttpEntity<>(json, headers);

        new RestTemplate().postForEntity(
                "http://localhost:8000/customer-db",
                entity,
                String.class);

        return new ApiResponse(200, "Customer created. ");
    }

    @Override
    public List<CustomerDTO> getCustomersByCreditsIds(List<Integer> creditsIds) {

        String ids = creditsIds.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));

        String url = "http://localhost:8000/customer-db/" + ids;

        ResponseEntity<CustomerDTO[]> responseEntity = new RestTemplate().getForEntity(
                url,
                CustomerDTO[].class
        );

        return Arrays.asList(Objects.requireNonNull(responseEntity.getBody()));
    }
}
