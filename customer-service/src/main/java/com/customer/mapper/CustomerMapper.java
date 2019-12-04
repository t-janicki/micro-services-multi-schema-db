package com.customer.mapper;

import com.customer.domain.Customer;
import com.customer.dto.CustomerDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerMapper {

    public CustomerDTO mapToCustomerDTO(Customer customer) {
        return new CustomerDTO(
                customer.getFirstName(),
                customer.getSurname(),
                customer.getPesel(),
                customer.getCreditId()
        );
    }

    public List<CustomerDTO> mapToCustomerDTOList(List<Customer> customers) {
        return customers.stream()
                .map(this::mapToCustomerDTO)
                .collect(Collectors.toList());
    }
}
