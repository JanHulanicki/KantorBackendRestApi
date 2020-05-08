package com.app.kantor.controller;

import com.app.kantor.domain.cart.CartCryptoDto;
import com.app.kantor.domain.crypto.CryptoCurrencyDto;
import com.app.kantor.domain.crypto.RealtimeCurrencyExchangeRatedDto;
import com.app.kantor.mapper.CartCryptoMapper;
import com.app.kantor.service.CartCryptoService;
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
@WebMvcTest(CartCryptoController.class)
public class CartCryptoControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CartCryptoService cartCryptoService;
    @MockBean
    private CartCryptoMapper cartCryptoMapper;

    @Test
    public void testCreateCryptoCart() throws Exception {
        //Given
        CartCryptoDto cartCryptoDto = new CartCryptoDto(1L, "2000-12-14", 2L);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(cartCryptoDto);

        //When & Then
        mockMvc.perform(post("/v1/cryptocart")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andDo(print());

        verify(cartCryptoService, times(1)).createCartCrypto(cartCryptoMapper.mapToCartCrypto(any()));
    }

    @Test
    public void testDeleteCryptoCart() throws Exception {
        //Given & When & Then
        mockMvc.perform(delete("/v1/cryptocart/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetCurrencyList() throws Exception {
        //Given
        List<CryptoCurrencyDto> currencyDtoList = new ArrayList<>();
        RealtimeCurrencyExchangeRatedDto realtimeCurrencyExchangeRatedDto = new RealtimeCurrencyExchangeRatedDto();
        realtimeCurrencyExchangeRatedDto.set_id(1L);
        realtimeCurrencyExchangeRatedDto.setCode("BTC");
        realtimeCurrencyExchangeRatedDto.setCurrency("BITCOIN");
        realtimeCurrencyExchangeRatedDto.setMid(new BigDecimal(10000));
        realtimeCurrencyExchangeRatedDto.setDate("2020-01-02");
        CryptoCurrencyDto cryptoCurrencyDto = new CryptoCurrencyDto(realtimeCurrencyExchangeRatedDto);
        currencyDtoList.add(cryptoCurrencyDto);
        when(cartCryptoService.getCryptoCurrencyFromCartCrypto(1l)).thenReturn(currencyDtoList);

        //When & Then
        mockMvc.perform(get("/v1/cryptocurrency/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.[0].realtimeCurrencyExchangeRatedDto._id", is(1)))
                .andExpect(jsonPath("$.[0].realtimeCurrencyExchangeRatedDto.code", is("BTC")));
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
        mockMvc.perform(post("/v1/cryptocurrency/1/2/3/2.0")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andDo(print());
        verify(cartCryptoService, times(1)).addCryptoCurrencyToCartCryptoProduct(1L, 2L, 3L, 2.0);
    }

    @Test
    public void testDeleteProductFromCartProduct() throws Exception {
        //Given & When & Then
        mockMvc.perform(delete("/v1/cryptocurrency/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }
}