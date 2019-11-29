package com.credit.facade;

import com.credit.dto.CreditDTO;
import com.credit.dto.CustomerDTO;
import com.credit.dto.ProductDTO;
import com.credit.service.CreditService;
import com.credit.web.request.CreditRequest;
import com.credit.web.response.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class CreditProductCustomerFacade {
    private CreditService creditService;

    @Autowired
    public CreditProductCustomerFacade(CreditService creditService) {
        this.creditService = creditService;
    }

    public Integer createCredit(CreditRequest request) throws JsonProcessingException {

        int creditId = creditService.generateCreditId();

        createProduct(request, creditId);

        createCustomer(request, creditId);

        creditService.saveNewCredit(request.getCreditName(), creditId);

        return creditId;
    }

    private ApiResponse createProduct(CreditRequest request, int creditId) throws JsonProcessingException {
        Map<String, String> productInfo = new HashMap<>();
        productInfo.put("productName", request.getProductName());
        productInfo.put("value", String.valueOf(request.getProductValue()));
        productInfo.put("creditId", String.valueOf(creditId));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String json = new ObjectMapper().writeValueAsString(productInfo);
        HttpEntity<String> entity = new HttpEntity<>(json, headers);

        ResponseEntity<ApiResponse> responseEntity = new RestTemplate().postForEntity(
                "http://localhost:8100/products",
                entity,
                ApiResponse.class);

        return responseEntity.getBody();
    }


    private ApiResponse createCustomer(CreditRequest request, int creditId) throws JsonProcessingException {
        Map<String, String> customerInfo = new HashMap<>();
        customerInfo.put("firstName", request.getFirstName());
        customerInfo.put("surname", request.getSurname());
        customerInfo.put("pesel", request.getPesel());
        customerInfo.put("creditId", String.valueOf(creditId));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String json = new ObjectMapper().writeValueAsString(customerInfo);
        HttpEntity<String> entity = new HttpEntity<>(json, headers);

        ResponseEntity<ApiResponse> responseEntity = new RestTemplate().postForEntity(
                "http://localhost:8200/customers",
                entity,
                ApiResponse.class);

        return responseEntity.getBody();
    }

    public List<CreditsDetailedResponse> getDetailedCreditsForCustomerByCreditsIds(List<Integer> creditsIds) {

        Map<Integer, CreditDTO> credits = creditService.getCredits()
                .stream()
                .filter(c -> creditsIds.stream()
                        .anyMatch(id -> id.equals(c.getCreditId())))
                .collect(Collectors.toMap(CreditDTO::getCreditId, c -> c));

        Map<Integer, ProductDTO> products = creditService.getProductsByCreditsIds(creditsIds)
                .stream()
                .collect(Collectors.toMap(ProductDTO::getCreditId, p -> p));

        Map<Integer, CustomerDTO> customers = creditService.getCustomersByCreditsIds(creditsIds)
                .stream()
                .collect(Collectors.toMap(CustomerDTO::getCreditId, c -> c));

        return credits.entrySet().stream()
                .map(v -> {
                    ProductDTO product = products.get(v.getKey());
                    CustomerDTO customer = customers.get(v.getKey());

                    return new CreditsDetailedResponse(
                            new CustomerResponse(
                                    customer.getFirstName(),
                                    customer.getSurname(),
                                    customer.getPesel()
                            ),
                            new ProductResponse(
                                    product.getProductName(),
                                    product.getValue()
                            ),
                            new CreditNameResponse(v.getValue().getCreditName())
                    );
                })
                .collect(Collectors.toList());
    }
}
