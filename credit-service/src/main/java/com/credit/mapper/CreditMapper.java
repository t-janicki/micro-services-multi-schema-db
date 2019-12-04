package com.credit.mapper;

import com.credit.domain.Credit;
import com.credit.dto.CreditDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CreditMapper {

    private CreditDTO mapToCreditDTO(Credit credit) {
        return new CreditDTO(
                credit.getCreditName(),
                credit.getCreditId()
        );
    }

    public List<CreditDTO> mapToCreditDTOList(List<Credit> credits) {
        return credits.stream()
                .map(this::mapToCreditDTO)
                .collect(Collectors.toList());
    }
}
