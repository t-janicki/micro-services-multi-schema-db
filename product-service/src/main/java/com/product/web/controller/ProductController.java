package com.product.web.controller;

import com.product.domain.Product;
import com.product.dto.ProductDTO;
import com.product.mapper.ProductMapper;
import com.product.service.ProductService;
import com.product.web.response.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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
    private Environment environment;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    public ProductController(ProductService productService,
                             ProductMapper productMapper,
                             Environment environment) {
        this.productService = productService;
        this.productMapper = productMapper;
        this.environment = environment;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> createProduct(@Valid @RequestBody ProductDTO request) {

        productService.saveNewProduct(request);

        LOGGER.info("Product service port " + Integer.parseInt(environment.getProperty("local.server.port")));

        return ResponseEntity.ok(new ApiResponse(200, "Product created. "));
    }

    @GetMapping(value = "/{creditsIds}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductDTO>> getProductsByCreditsIds(@PathVariable List<Integer> creditsIds) {

        List<Product> products = productService.getProductsByCreditsIds(creditsIds);

        LOGGER.info("Product service port " + Integer.parseInt(environment.getProperty("local.server.port")));

        return ResponseEntity.ok(productMapper.mapToProductDTOLIst(products));
    }
}
