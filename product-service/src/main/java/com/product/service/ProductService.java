package com.product.service;

import com.product.domain.Product;
import com.product.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    Product saveNewProduct(ProductDTO productDTO);

    List<Product> getProductsByCreditsIds(List<Integer> creditsIds);
}
