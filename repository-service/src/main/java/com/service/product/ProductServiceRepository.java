package com.service.product;

import com.domain.product.Product;
import com.dto.ProductDTO;

import java.util.List;

public interface ProductServiceRepository {

    Product saveNewProduct(ProductDTO productDTO);

    List<Product> getProductsByCreditsIds(List<Integer> creditsIds);
}
