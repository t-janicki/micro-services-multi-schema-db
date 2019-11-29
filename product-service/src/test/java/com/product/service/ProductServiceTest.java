package com.product.service;

import com.product.domain.Product;
import com.product.dto.ProductDTO;
import com.product.repository.ProductRepository;
import com.product.service.impl.ProductServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductServiceTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldCreateNewProduct() {
        //GIVEN
        ProductDTO productDTO = new ProductDTO("Product name", 2000, 3123123);

        Product product = new Product();
        product.setCreditId(productDTO.getCreditId());
        product.setProductName(productDTO.getProductName());
        product.setValue(productDTO.getValue());

        //WHEN
        Product result = productService.saveNewProduct(productDTO);

        //THEN
        assertEquals("Product name", result.getProductName());
        assertEquals(2000, result.getValue().intValue());
        assertEquals(3123123, result.getCreditId().intValue());
    }

    @Test
    public void shouldFetchGetProductsByCreditsIds() {
        //GIVEN
        Product product1 = new Product("Product 1", 200, 562351234);
        Product product2 = new Product("Product 2", 400000, 312341312);
        Product product3 = new Product("Product 3", 5000, 995872);

        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        products.add(product3);

        List<Integer> creditsIds = Arrays.asList(562351234, 312341312, 995872);

        when(productRepository.findAllByCreditIdIn(creditsIds)).thenReturn(products);

        //WHEN
        List<Product> result = productService.getProductsByCreditsIds(creditsIds);

        //THEN
        assertEquals(products.size(), result.size());
        verify(productRepository, times(1)).findAllByCreditIdIn(creditsIds);
    }
}
