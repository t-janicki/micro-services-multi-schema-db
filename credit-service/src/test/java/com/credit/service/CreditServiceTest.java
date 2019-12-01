//package com.credit.service;
//
//import com.credit.dto.ProductDTO;
//import com.credit.repository.CreditRepository;
//import com.credit.service.impl.CreditServiceImpl;
//import com.credit.web.request.CreditRequest;
//import com.credit.web.response.CreditsDetailedResponse;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.ArgumentMatchers;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.powermock.core.classloader.annotations.PrepareForTest;
//import org.powermock.modules.junit4.PowerMockRunner;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//import static org.powermock.api.mockito.PowerMockito.*;
//
//@RunWith(PowerMockRunner.class)
//@PrepareForTest(CreditServiceImpl.class)
//@WebMvcTest(CreditServiceImpl.class)
//public class CreditServiceTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private CreditServiceImpl creditService;
//
//    @Mock
//    private CreditRepository creditRepository;
//
//    @Before
//    public void init() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void shouldCreateCredit() throws Exception {
//        //GIVEN
//        CreditRequest request = new CreditRequest(
//                "First name",
//                "Surname",
//                "Pesel",
//                "Product name",
//                20003,
//                "Credit name"
//        );
//
//        int creditId = 3123123;
//        List<ProductDTO> productsDTO = new ArrayList<>();
//
//        CreditServiceImpl mock = spy(new CreditServiceImpl());
//
//
//        when(mock, "getProductsByCreditsIds", Arrays.asList(41231312, 3312)).thenReturn(productsDTO);
//
//        //WHEN
//        Integer result = creditService.createCredit(request);
//
//        //THEN
//        assertEquals(3123, 3231);
//
//
//    }
//}
