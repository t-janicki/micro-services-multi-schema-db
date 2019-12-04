package com.product.controller;

import com.google.gson.Gson;
import com.product.domain.Product;
import com.product.dto.ProductDTO;
import com.product.mapper.ProductMapper;
import com.product.service.ProductService;
import com.product.web.controller.ProductController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.Matchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @MockBean
    private ProductMapper productMapper;

    @Test
    public void shouldFetchCreateProduct() throws Exception {
        //GIVEN
        ProductDTO productDTO = new ProductDTO("Product name", 200000, 412974);

        Gson gson = new Gson();
        String json = gson.toJson(productDTO);

        //WHEN & THEN
        mockMvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("Product created. ")))
                .andExpect(jsonPath("$.status", is(200)))
                .andReturn();
    }

    @Test
    public void shouldFetchGetProductsByCreditsIds() throws Exception {
        //GIVEN
        List<ProductDTO> productDTOList = new ArrayList<>();
        productDTOList.add(new ProductDTO("Product name 1", 200000, 412974));
        productDTOList.add(new ProductDTO("Product name 2 ", 500, 5123));
        productDTOList.add(new ProductDTO("Product name 2", 95800, 12355));

        List<Product> products = new ArrayList<>();
        products.add(new Product("Product name 1", 200000, 412974));
        products.add(new Product("Product name 2 ", 500, 5123));
        products.add(new Product("Product name 2", 95800, 12355));

        when(productService.getProductsByCreditsIds(Arrays.asList(412974, 5123, 12355))).thenReturn(products);
        when(productMapper.mapToProductDTOLIst(products)).thenReturn(productDTOList);

        //WHEN & THEN
        mockMvc.perform(get("/products/412974,5123,12355")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andReturn();
    }

    @Test
    public void shouldFetchEmptyProductsList() throws Exception {
        //GIVEN
        List<ProductDTO> productsDTO = new ArrayList<>();
        List<Product> products = new ArrayList<>();

        when(productService.getProductsByCreditsIds(Arrays.asList(412974, 5123, 12355))).thenReturn(products);
        when(productMapper.mapToProductDTOLIst(products)).thenReturn(productsDTO);

        //WHEN & THEN
        mockMvc.perform(get("/products/412974,5123,12355")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)))
                .andReturn();
    }
}
