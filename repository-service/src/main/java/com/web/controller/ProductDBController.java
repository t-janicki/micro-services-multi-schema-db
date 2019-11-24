package com.web.controller;

import com.domain.product.Product;
import com.dto.ProductDTO;
import com.mapper.ProductMapper;
import com.service.product.ProductServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/product-db")
@Transactional
public class ProductDBController {

    private ProductServiceRepository serviceRepository;
    private ProductMapper productMapper;

    @Autowired
    public ProductDBController(ProductServiceRepository serviceRepository,
                               ProductMapper productMapper) {
        this.serviceRepository = serviceRepository;
        this.productMapper = productMapper;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public String createProduct(@RequestBody ProductDTO request) {

        serviceRepository.saveNewProduct(request);

        return "Product saved";
    }

    @GetMapping(value = "/{creditsIds}", produces = APPLICATION_JSON_VALUE)
    public List<ProductDTO> getProductsByCreditsIds(@PathVariable List<Integer> creditsIds) {
        List<Product> products = serviceRepository.getProductsByCreditsIds(creditsIds);

        return productMapper.mapToProductDTOLIst(products);
    }
}
