package com.product.controller;

import com.product.domain.Product;
import com.product.dto.ProductDTO;
import com.product.repository.ProductRepository;
import com.product.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerTestSuite {
    @LocalServerPort
    int randomServerPort;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @Test
    void test() throws URISyntaxException {

//        RestTemplate restTemplate = new RestTemplate();

        String baseUrl = "http://localhost:" + randomServerPort + "/products";

        ProductDTO requestDTO = new ProductDTO(
                "Product name",
                20000,
                31314123
        );

        URI uri = new URI(baseUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ProductDTO> request = new HttpEntity<>(requestDTO, headers);

        ResponseEntity<Product> result = restTemplate.postForEntity(uri, request, Product.class);

        //Verify request succeed
        assertEquals(200, result.getStatusCodeValue());
        assertEquals("Product name", Objects.requireNonNull(result.getBody()).getProductName());
    }
}
