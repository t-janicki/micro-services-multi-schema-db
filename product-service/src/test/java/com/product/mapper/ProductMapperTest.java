package com.product.mapper;

import com.product.domain.Product;
import com.product.dto.ProductDTO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotSame;

@SpringBootTest
public class ProductMapperTest {
    @InjectMocks
    private ProductMapper productMapper;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void mapToProductDTO() {
        //GIVEN
        Product product = new Product();

        //WHEN
        ProductDTO productDTO = productMapper.mapToProductDTO(product);

        //THEN
        assertNotSame(product, productDTO);
    }

    @Test
    public void mapTOProductDTOList() {
        //GIVEN
        List<Product> productsList = new ArrayList<>();

        //WHEN
        List<ProductDTO> productsDTOList = productMapper.mapToProductDTOLIst(productsList);

        //THEN
        assertNotSame(productsList, productsDTOList);
    }
}
