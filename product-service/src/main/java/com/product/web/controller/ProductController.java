package com.product.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.product.dto.ProductDTO;
import com.product.service.ProductService;
import com.product.web.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> createProduct(@Valid @RequestBody ProductDTO request) throws JsonProcessingException {

        ApiResponse response = productService.createProduct(request);

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{creditsIds}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductDTO>> getProductsByCreditsIds(@PathVariable List<Integer> creditsIds) {
        return ResponseEntity.ok(productService.getProductsByCreditsIds(creditsIds));
    }
}
