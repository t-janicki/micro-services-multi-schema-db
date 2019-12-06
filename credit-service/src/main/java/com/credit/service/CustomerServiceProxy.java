package com.credit.service;

import com.credit.dto.CustomerDTO;
import com.credit.web.response.ApiResponse;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

//@FeignClient(name = "customer-service", url = "localhost:8200")
//@FeignClient(name = "customer-service")
@FeignClient(name = "netflix-zuul-service")
@RibbonClient(name = "customer-service")
public interface CustomerServiceProxy {

    @PostMapping(value = "/customer-service/customers")
    ApiResponse registerCustomer(@RequestBody CustomerDTO request);

    @GetMapping(value = "/customer-service/customers/{creditsIds}")
    List<CustomerDTO> getCustomersByCreditsIds(@PathVariable("creditsIds") Integer[] creditsIds);
}
