package com.credit.service;

import com.credit.domain.Credit;
import com.credit.dto.CustomerDTO;
import com.credit.dto.ProductDTO;

import java.util.List;

public interface CreditService {

    int generateCreditId();

    Credit saveNewCredit(String creditName, int creditId);

    List<Credit> getCredits();

    List<CustomerDTO> getCustomersByCreditsIds(List<Integer> creditsIds);

    List<ProductDTO> getProductsByCreditsIds(List<Integer> creditsIds);
}
