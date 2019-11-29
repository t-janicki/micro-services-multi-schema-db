package com.product.service;

import com.product.domain.Product;
import com.product.dto.ProductDTO;
import com.product.web.response.ApiResponse;

import java.util.List;

public interface ProductService {

    ApiResponse saveNewProduct(ProductDTO productDTO);

    List<Product> getProductsByCreditsIds(List<Integer> creditsIds);
}
