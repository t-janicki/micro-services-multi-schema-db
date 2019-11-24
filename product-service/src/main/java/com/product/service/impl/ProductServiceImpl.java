package com.product.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.dto.ProductDTO;
import com.product.service.ProductService;
import com.product.web.response.ApiResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Override
    public ApiResponse createProduct(ProductDTO request) throws JsonProcessingException {

        Map<String, String> productInfo = new HashMap<>();
        productInfo.put("productName", request.getProductName());
        productInfo.put("value", Integer.toString(request.getValue()));
        productInfo.put("creditId", Integer.toString(request.getCreditId()));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String json = new ObjectMapper().writeValueAsString(productInfo);
        HttpEntity<String> entity = new HttpEntity<>(json, headers);

        new RestTemplate().postForEntity(
                "http://localhost:8000/product-db",
                entity,
                String.class);

        return new ApiResponse(200, "Product created. ");
    }

    @Override
    public List<ProductDTO> getProductsByCreditsIds(List<Integer> creditsIds) {

        String ids = creditsIds.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));

        String url = "http://localhost:8000/product-db/" + ids;

        ResponseEntity<ProductDTO[]> responseEntity = new RestTemplate().getForEntity(
                url,
                ProductDTO[].class
        );

        return Arrays.asList(Objects.requireNonNull(responseEntity.getBody()));
    }
}
