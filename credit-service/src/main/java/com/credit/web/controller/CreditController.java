package com.credit.web.controller;

import com.credit.service.CreditService;
import com.credit.web.request.CreditRequest;
import com.credit.web.response.CreditIdResponse;
import com.credit.web.response.CreditsDetailedResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/credits")
public class CreditController {

    private CreditService creditService;

    @Autowired
    public CreditController(CreditService creditService) {
        this.creditService = creditService;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CreditIdResponse> createCredit(@Valid @RequestBody CreditRequest request) throws JsonProcessingException {

        int creditId = creditService.createCredit(request);

        return ResponseEntity.ok(new CreditIdResponse(creditId));
    }

    @GetMapping(value = "/creditsIds={creditsIds}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CreditsDetailedResponse>> getCredits(@PathVariable List<Integer> creditsIds) {

        return ResponseEntity.ok(creditService.getDetailedCreditsForCustomerByCreditsIds(creditsIds));
    }

}
