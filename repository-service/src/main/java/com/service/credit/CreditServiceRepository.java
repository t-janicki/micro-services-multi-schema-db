package com.service.credit;

import com.domain.credit.Credit;
import com.dto.CreditDTO;

import java.util.List;

public interface CreditServiceRepository {

    Credit saveNewCredit(CreditDTO creditDTO);

    List<Credit> getCredits();
}
