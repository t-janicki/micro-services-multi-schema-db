package com.service.credit.impl;

import com.domain.credit.Credit;
import com.dto.CreditDTO;
import com.repository.credit.CreditRepository;
import com.service.credit.CreditServiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CreditServiceRepositoryImpl implements CreditServiceRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreditServiceRepositoryImpl.class);

    private CreditRepository creditRepository;

    @Autowired
    public CreditServiceRepositoryImpl(CreditRepository creditRepository) {
        this.creditRepository = creditRepository;
    }

    @Override
    public Credit saveNewCredit(CreditDTO creditDTO) {
        Credit credit = new Credit();
        credit.setCreditId(creditDTO.getCreditId());
        credit.setCreditName(creditDTO.getCreditName());

        creditRepository.save(credit);

        LOGGER.info("New credit saved. ");
        return credit;
    }

    @Override
    public List<Credit> getCredits() {
        return creditRepository.findAll();
    }


}
