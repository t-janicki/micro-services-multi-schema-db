package com.product.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.product.dto.ProductDTO;
import com.product.web.response.ApiResponse;

import java.util.List;

public interface ProductService {

    ApiResponse createProduct(ProductDTO productDTO) throws JsonProcessingException;

    List<ProductDTO> getProductsByCreditsIds(List<Integer> creditsIds);
}
