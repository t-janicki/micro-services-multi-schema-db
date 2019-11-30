package com.credit.service.impl;

import com.credit.domain.Credit;
import com.credit.dto.CreditDTO;
import com.credit.dto.CustomerDTO;
import com.credit.dto.ProductDTO;
import com.credit.mapper.CreditMapper;
import com.credit.repository.CreditRepository;
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
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.security.SecureRandom;
import java.util.*;
import java.util.stream.Collectors;

import static org.hibernate.bytecode.BytecodeLogger.LOGGER;

@Service
public class CreditServiceImpl implements CreditService {
    private CreditRepository creditRepository;
    private CreditMapper creditMapper;

    @Autowired
    public CreditServiceImpl(CreditRepository creditRepository,
                             CreditMapper creditMapper) {
        this.creditRepository = creditRepository;
        this.creditMapper = creditMapper;
    }

    public Integer createCredit(CreditRequest request) throws JsonProcessingException {

        int creditId = generateCreditId();

        createProduct(request, creditId);

        createCustomer(request, creditId);

        saveNewCredit(request.getCreditName(), creditId);

        return creditId;
    }

    public List<CreditsDetailedResponse> getDetailedCreditsForCustomerByCreditsIds(List<Integer> creditsIds) {

        List<CreditDTO> creditDTO = creditMapper.mapToCreditDTOList(getCredits());

        Map<Integer, CreditDTO> credits = creditDTO
                .stream()
                .filter(c -> creditsIds.stream()
                        .anyMatch(id -> id.equals(c.getCreditId())))
                .collect(Collectors.toMap(CreditDTO::getCreditId, c -> c));

        Map<Integer, ProductDTO> productsDTO = getProductsByCreditsIds(creditsIds)
                .stream()
                .collect(Collectors.toMap(ProductDTO::getCreditId, p -> p));

        Map<Integer, CustomerDTO> customersDTO = getCustomersByCreditsIds(creditsIds)
                .stream()
                .collect(Collectors.toMap(CustomerDTO::getCreditId, c -> c));

        return credits.entrySet().stream()
                .map(v -> {
                    ProductDTO product = productsDTO.get(v.getKey());
                    CustomerDTO customer = customersDTO.get(v.getKey());

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

    private List<Credit> getCredits() {
        return creditRepository.findAll();
    }

    private int generateCreditId() {
        return Math.abs(new SecureRandom().nextInt());
    }

    private List<CustomerDTO> getCustomersByCreditsIds(List<Integer> creditsIds) {
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

    private List<ProductDTO> getProductsByCreditsIds(List<Integer> creditsIds) {
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

    private Credit saveNewCredit(String creditName, int creditId) {
        Credit credit = new Credit();
        credit.setCreditId(creditId);
        credit.setCreditName(creditName);

        creditRepository.save(credit);

        LOGGER.info("New credit saved. ");
        return credit;
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

}
