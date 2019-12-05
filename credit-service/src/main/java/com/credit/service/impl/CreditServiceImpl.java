package com.credit.service.impl;

import com.credit.domain.Credit;
import com.credit.dto.CreditDTO;
import com.credit.dto.CustomerDTO;
import com.credit.dto.ProductDTO;
import com.credit.mapper.CreditMapper;
import com.credit.repository.CreditRepository;
import com.credit.service.CreditService;
import com.credit.service.CustomerServiceProxy;
import com.credit.service.ProductServiceProxy;
import com.credit.web.request.CreditRequest;
import com.credit.web.response.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CreditServiceImpl implements CreditService {
    private CreditRepository creditRepository;
    private CreditMapper creditMapper;
    private CustomerServiceProxy customerServiceProxy;
    private ProductServiceProxy productServiceProxy;

    private static final Logger LOGGER = LoggerFactory.getLogger(CreditServiceImpl.class);

    @Autowired
    public CreditServiceImpl(CreditRepository creditRepository,
                             CreditMapper creditMapper,
                             CustomerServiceProxy customerServiceProxy,
                             ProductServiceProxy productServiceProxy) {
        this.creditRepository = creditRepository;
        this.creditMapper = creditMapper;
        this.customerServiceProxy = customerServiceProxy;
        this.productServiceProxy = productServiceProxy;
    }

    public Integer createCredit(CreditRequest request) {

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

        Map<Integer, ProductDTO> productsDTO = getProductsByCreditsIds(creditsIds);

        Map<Integer, CustomerDTO> customersDTO = getCustomersByCreditsIdsAsMap(creditsIds);

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

    private Map<Integer, CustomerDTO> getCustomersByCreditsIdsAsMap(List<Integer> creditsIds) {
        List<CustomerDTO> customersDTO = customerServiceProxy.getCustomersByCreditsIds(creditsIds.toArray(new Integer[0]));

        return customersDTO.stream()
                .collect(Collectors.toMap(CustomerDTO::getCreditId, c -> c));
    }

    private Map<Integer, ProductDTO> getProductsByCreditsIds(List<Integer> creditsIds) {
        List<ProductDTO> productsDTO = productServiceProxy.getProductsByCreditsIds(creditsIds.toArray(new Integer[0]));

        return productsDTO.stream()
                .collect(Collectors.toMap(ProductDTO::getCreditId, p -> p));
    }

    private Credit saveNewCredit(String creditName, int creditId) {
        Credit credit = new Credit();
        credit.setCreditId(creditId);
        credit.setCreditName(creditName);

        creditRepository.save(credit);

        LOGGER.info("New credit saved. ");
        return credit;
    }

    private ApiResponse createProduct(CreditRequest request, int creditId) {
        ProductDTO productDTO = new ProductDTO(
                request.getProductName(),
                request.getProductValue(),
                creditId
        );

        return productServiceProxy.registerProduct(productDTO);
    }


    private ApiResponse createCustomer(CreditRequest request, int creditId) {
        CustomerDTO customerDTO = new CustomerDTO(
                request.getFirstName(),
                request.getSurname(),
                request.getPesel(),
                creditId
        );

        return customerServiceProxy.registerCustomer(customerDTO);
    }

}
