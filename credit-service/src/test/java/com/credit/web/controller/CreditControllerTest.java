package com.credit.web.controller;

import com.credit.domain.Credit;
import com.credit.mapper.CreditMapper;
import com.credit.repository.CreditRepository;
import com.credit.service.CreditService;
import com.credit.web.request.CreditRequest;
import com.google.gson.Gson;
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

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CreditController.class)
public class CreditControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreditService creditService;

    @MockBean
    private CreditMapper creditMapper;

    @MockBean
    private CreditRepository creditRepository;

    @Test
    public void shouldFetchCreateCredit() throws Exception {
        //GIVEN
        CreditRequest request = new CreditRequest(
                "First name",
                "Surname",
                "85987237",
                "Product name",
                2003,
                "Credit name"
        );

        Gson gson = new Gson();
        String json = gson.toJson(request);

        when(creditService.createCredit(request)).thenReturn(4123123);

        //WHEN & THEN
        mockMvc.perform(post("/credits")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    /**
     *
     * Implementation to fix
     */
    @Test
    public void shouldFetchGetCreditsByCreditsIds() throws Exception {
        //GIVEN
//        List<Credit> credits = new ArrayList<>();
//        credits.add(new Credit("Credit 1", 3819823));
//        credits.add(new Credit("Credit 2", 39801823));
//        credits.add(new Credit("Credit 3", 323123));
//
////        List<Integer> creditsIds = Arrays.asList(3819823, 39801823, 323123);

//        when(creditRepository.findAll()).thenReturn(credits);

        //WHEN & THEN
        mockMvc.perform(get("/credits/creditsIds=3819823,39801823,323123")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)))
                .andExpect(status().isOk());
    }
}
