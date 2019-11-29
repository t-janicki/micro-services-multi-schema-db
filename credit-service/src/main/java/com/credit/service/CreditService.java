package com.credit.service;

import com.credit.dto.CreditDTO;
import com.credit.dto.CustomerDTO;
import com.credit.dto.ProductDTO;
import com.credit.web.response.ApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface CreditService {

    int generateCreditId();

    ApiResponse saveNewCredit(String creditName, Integer creditId) throws JsonProcessingException;

    List<CreditDTO> getCredits();

    List<CustomerDTO> getCustomersByCreditsIds(List<Integer> creditsIds);

    List<ProductDTO> getProductsByCreditsIds(List<Integer> creditsIds);
}
