package com.app.kantor.controller;

import com.app.kantor.domain.cart.CartNbpDto;
import com.app.kantor.domain.nbp.NbpCurrencyDto;
import com.app.kantor.domain.nbp.NbpCurrencyRatesDto;
import com.app.kantor.mapper.CartNbpMapper;
import com.app.kantor.service.CartNbpService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CartNbpController.class)
public class CartNbpControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CartNbpService cartNbpService;
    @MockBean
    private CartNbpMapper cartNbpMapper;
    //   @Before


    @Test
    public void testCreateNbpCart() throws Exception {
        //Given
        CartNbpDto cartNbpDto = new CartNbpDto(1L, "2000-12-14", 2L);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(cartNbpDto);

        //When & Then
        mockMvc.perform(post("/v1/nbpCart")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andDo(print());

        verify(cartNbpService, times(1)).createCartNbp(cartNbpMapper.mapToCartNbp(any()));
    }

    @Test
    public void testDeleteNbpCart() throws Exception {
        //Given & When & Then
        mockMvc.perform(delete("/v1/nbpcart/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetCurrencyList() throws Exception {
        //Given
        List<NbpCurrencyDto> currencyDtoList = new ArrayList<>();
        NbpCurrencyRatesDto[] rates = new NbpCurrencyRatesDto[1];
        rates[0] = new NbpCurrencyRatesDto();
        rates[0].setEffectiveDate("2020-01-02");
        rates[0].setMid(new BigDecimal(4.6));
        NbpCurrencyDto nbpCurrencyDto = new NbpCurrencyDto(1l, "euro", "EUR", rates);
        currencyDtoList.add(nbpCurrencyDto);
        when(cartNbpService.getNbpCurrencyFromCartNbp(1l)).thenReturn(currencyDtoList);


        //When & Then
        mockMvc.perform(get("/v1/nbpcurrency/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0]._id", is(1)))
                .andExpect(jsonPath("$[0].code", is("EUR")));
    }

    @Test
    public void testAddProductToCartProduct() throws Exception {
        //Given

        Object addProdContrParam = new Object() {
            public final Long cartProductId = 1L;
            public final Long cartNbpId = 2L;
            public final Long productId = 3L;
            public final Double amount = 2.0;

        };
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(addProdContrParam);

        //When & Then
        mockMvc.perform(post("/v1/nbpcurrency/1/2/3/2.0")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andDo(print());
        verify(cartNbpService, times(1)).addNbpCurrencyToCartNbpProduct(1L, 2L, 3L, 2.0);
    }
}
