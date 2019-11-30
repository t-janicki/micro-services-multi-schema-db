package com.credit.service;

import com.credit.web.request.CreditRequest;
import com.credit.web.response.CreditsDetailedResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface CreditService {

    Integer createCredit(CreditRequest request) throws JsonProcessingException;

    List<CreditsDetailedResponse> getDetailedCreditsForCustomerByCreditsIds(List<Integer> creditsIds);
}
