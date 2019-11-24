package com.web.controller;

import com.domain.credit.Credit;
import com.dto.CreditDTO;
import com.mapper.CreditMapper;
import com.service.credit.CreditServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/credit-db")
@Transactional
public class CreditDBController {

    private CreditServiceRepository creditServiceRepository;
    private CreditMapper creditMapper;

    @Autowired
    public CreditDBController(CreditServiceRepository creditServiceRepository,
                              CreditMapper creditMapper) {
        this.creditServiceRepository = creditServiceRepository;
        this.creditMapper = creditMapper;
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public String createCredit(@RequestBody CreditDTO creditDTO) {
        creditServiceRepository.saveNewCredit(creditDTO);
        return "Credit saved";
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<CreditDTO> getCredits() {
        List<Credit> credits = creditServiceRepository.getCredits();

        return creditMapper.mapToCreditDTOList(credits);
    }
}
