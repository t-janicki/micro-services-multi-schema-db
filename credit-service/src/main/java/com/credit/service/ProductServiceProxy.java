package com.credit.service;

import com.credit.dto.ProductDTO;
import com.credit.web.response.ApiResponse;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

//@FeignClient(name = "product-service", url = "localhost:8100")
//@FeignClient(name = "product-service")
@FeignClient(name = "netflix-zuul-service")
@RibbonClient(name = "product-service")
public interface ProductServiceProxy {

    //    @PostMapping(value = "/products")
    @PostMapping(value = "/product-service/products")
    ApiResponse registerProduct(@RequestBody ProductDTO productDTO);

    //    @GetMapping(value = "/products/{creditsIds}")
    @GetMapping(value = "/product-service/products/{creditsIds}")
    List<ProductDTO> getProductsByCreditsIds(@PathVariable("creditsIds") Integer[] creditsIds);
}
