package com.service.product.impl;

import com.domain.product.Product;
import com.dto.ProductDTO;
import com.repository.product.ProductRepository;
import com.service.product.ProductServiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceRepositoryImpl implements ProductServiceRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceRepositoryImpl.class);
    private ProductRepository productRepository;

    @Autowired
    public ProductServiceRepositoryImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product saveNewProduct(ProductDTO productDTO) {

        Product product = new Product();
        product.setProductName(productDTO.getProductName());
        product.setValue(productDTO.getValue());
        product.setCreditId(productDTO.getCreditId());

        productRepository.save(product);

        LOGGER.info("New product saved. ");
        return product;
    }

    @Override
    public List<Product> getProductsByCreditsIds(List<Integer> creditsIds) {
        return productRepository.findAllByCreditIdIn(creditsIds);
    }
}
