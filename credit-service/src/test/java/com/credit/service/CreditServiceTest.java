package com.credit.service;

import com.credit.repository.CreditRepository;
import com.credit.service.impl.CreditServiceImpl;
import com.credit.web.request.CreditRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@PrepareForTest(CreditServiceImpl.class)
public class CreditServiceTest {

    @InjectMocks
    private CreditServiceImpl creditService;

    @Mock
    private CreditRepository creditRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldCreateCredit() throws Exception {
        //GIVEN
        CreditRequest request = new CreditRequest(
                "First name",
                "Surname",
                "Pesel",
                "Product name",
                20003,
                "Credit name"
        );

        int creditId = 3123123;

        //WHEN
        Integer result = creditService.createCredit(request);

        //THEN
        assertEquals(3123, 3231);


    }
}
