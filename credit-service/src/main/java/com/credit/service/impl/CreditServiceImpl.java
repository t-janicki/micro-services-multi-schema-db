package com.credit.service.impl;

import com.credit.dto.CreditDTO;
import com.credit.dto.CustomerDTO;
import com.credit.dto.ProductDTO;
import com.credit.service.CreditService;
import com.credit.web.response.ApiResponse;
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
public class CreditServiceImpl implements CreditService {

    @Override
    public ApiResponse saveNewCredit(String creditName, Integer creditId) throws JsonProcessingException {

        Map<String, String> creditInfo = new HashMap<>();
        creditInfo.put("creditName", creditName);
        creditInfo.put("creditId", String.valueOf(creditId));

        HttpHeaders headers=  new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String json = new ObjectMapper().writeValueAsString(creditInfo);
        HttpEntity<String> entity = new HttpEntity<>(json, headers);

        new RestTemplate().postForEntity(
                "http://localhost:8000/credit-db",
                entity,
                String.class
        );

        return new ApiResponse(200, "Product saved. ");
    }

    @Override
    public List<CreditDTO> getCredits() {
        ResponseEntity<CreditDTO[]> responseEntity = new RestTemplate().getForEntity(
                "http://localhost:8000/credit-db",
                CreditDTO[].class
        );

        return Arrays.asList(Objects.requireNonNull(responseEntity.getBody()));
    }

    @Override
    public List<CustomerDTO> getCustomersByCreditsIds(List<Integer> creditsIds) {
        String ids = creditsIds.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));

        String url = "http://localhost:8200/customers/" + ids;

        ResponseEntity<CustomerDTO[]> responseEntity = new RestTemplate().getForEntity(
                url,
                CustomerDTO[].class
        );

        return Arrays.asList(Objects.requireNonNull(responseEntity.getBody()));
    }

    @Override
    public List<ProductDTO> getProductsByCreditsIds(List<Integer> creditsIds) {
        String ids = creditsIds.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));

        String url = "http://localhost:8100/products/" + ids;

        ResponseEntity<ProductDTO[]> responseEntity = new RestTemplate().getForEntity(
                url,
                ProductDTO[].class
        );

        return Arrays.asList(Objects.requireNonNull(responseEntity.getBody()));
    }


}
