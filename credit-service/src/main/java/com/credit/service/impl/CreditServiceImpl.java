package com.credit.service.impl;

import com.credit.domain.Credit;
import com.credit.dto.CustomerDTO;
import com.credit.dto.ProductDTO;
import com.credit.repository.CreditRepository;
import com.credit.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public CreditServiceImpl(CreditRepository creditRepository) {
        this.creditRepository = creditRepository;
    }

    @Override
    public int generateCreditId() {
        return Math.abs(new SecureRandom().nextInt());
    }

    @Override
    public Credit saveNewCredit(String creditName, int creditId) {
        Credit credit = new Credit();
        credit.setCreditId(creditId);
        credit.setCreditName(creditName);

        creditRepository.save(credit);

        LOGGER.info("New credit saved. ");
        return credit;
    }

    @Override
    public List<Credit> getCredits() {
        return creditRepository.findAll();
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
