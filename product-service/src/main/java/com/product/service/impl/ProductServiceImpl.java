package com.product.service.impl;

import com.product.domain.Product;
import com.product.dto.ProductDTO;
import com.product.repository.ProductRepository;
import com.product.service.ProductService;
import com.product.web.response.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ApiResponse saveNewProduct(ProductDTO productDTO) {

        Product product = new Product();
        product.setProductName(productDTO.getProductName());
        product.setValue(productDTO.getValue());
        product.setCreditId(productDTO.getCreditId());

        productRepository.save(product);

        LOGGER.info("New product saved. ");

        return new ApiResponse(200, "Product created. ");
    }

    @Override
    public List<Product> getProductsByCreditsIds(List<Integer> creditsIds) {
        return productRepository.findAllByCreditIdIn(creditsIds);
    }
}
