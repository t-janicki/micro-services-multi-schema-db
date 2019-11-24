package com.mapper;

import com.domain.product.Product;
import com.dto.ProductDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    private ProductDTO mapToProductDTO(Product product) {
        return new ProductDTO(
                product.getProductName(),
                product.getValue(),
                product.getCreditId()
        );
    }

    public List<ProductDTO> mapToProductDTOLIst(List<Product> products) {
        return products.stream()
                .map(this::mapToProductDTO)
                .collect(Collectors.toList());
    }
}
