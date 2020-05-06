package com.app.kantor.controller;

import com.app.kantor.domain.crypto.CryptoCurrencyDto;
import com.app.kantor.domain.crypto.RealtimeCurrencyExchangeRatedDto;
import com.app.kantor.facade.valut.CryptoFacade;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CryptoController.class)
public class CryptoCurrencyControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CryptoFacade cryptoFacade;


    @Test
    public void testGetActualNbpRate() throws Exception {
        //Given

        RealtimeCurrencyExchangeRatedDto realtimeCurrencyExchangeRatedDto = new RealtimeCurrencyExchangeRatedDto();
        realtimeCurrencyExchangeRatedDto.set_id(1L);
        realtimeCurrencyExchangeRatedDto.setCode("BTC");
        realtimeCurrencyExchangeRatedDto.setCurrency("BITCOIN");
        realtimeCurrencyExchangeRatedDto.setMid(new BigDecimal(10000));
        realtimeCurrencyExchangeRatedDto.setDate("2020-01-02");
        CryptoCurrencyDto cryptoCurrencyDto = new CryptoCurrencyDto(realtimeCurrencyExchangeRatedDto);

        when(cryptoFacade.getCryptoCurrency("BTC")).thenReturn(cryptoCurrencyDto);

        //When & Then
        mockMvc.perform(get("/v1/crypto/BTC")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.realtimeCurrencyExchangeRatedDto._id", is(1)))
                .andExpect(jsonPath("$.realtimeCurrencyExchangeRatedDto.code", is("BTC")));
    }

    @Test
    public void testgetAllCurrencyList() throws Exception {
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
        when(cryptoFacade.getAllCryptoCurrency()).thenReturn(currencyDtoList);

        //When & Then
        mockMvc.perform(get("/v1/crypto")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.[0].realtimeCurrencyExchangeRatedDto._id", is(1)))
                .andExpect(jsonPath("$.[0].realtimeCurrencyExchangeRatedDto.code", is("BTC")));
    }

    @Test
    public void testSaveCryptoCurrencyRate() throws Exception {
        //Given
        CryptoCurrencyDto currencyDto = new CryptoCurrencyDto();
        Gson gson = new Gson();
        String jsonContent = gson.toJson(currencyDto);
        //When & Then
        mockMvc.perform(post("/v1/crypto")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andDo(print());
        verify(cryptoFacade, times(1)).saveCryptoCurrency(any());
    }
}