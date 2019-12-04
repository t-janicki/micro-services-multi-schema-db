package com.product.web.controller;

import com.product.domain.Product;
import com.product.dto.ProductDTO;
import com.product.mapper.ProductMapper;
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
    private ProductMapper productMapper;

    @Autowired
    public ProductController(ProductService productService,
                             ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> createProduct(@Valid @RequestBody ProductDTO request) {

        ApiResponse response = productService.saveNewProduct(request);

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{creditsIds}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductDTO>> getProductsByCreditsIds(@PathVariable List<Integer> creditsIds) {

        List<Product> products = productService.getProductsByCreditsIds(creditsIds);

        return ResponseEntity.ok(productMapper.mapToProductDTOLIst(products));
    }
}
