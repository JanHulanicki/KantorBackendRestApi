package com.app.kantor.controller;

import com.app.kantor.domain.nbp.NbpCurrencyDto;
import com.app.kantor.domain.nbp.NbpCurrencyRatesDto;
import com.app.kantor.facade.valut.NbpFacade;
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
@WebMvcTest(NbpCurrencyController.class)
public class NbpCurrencyControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private NbpFacade nbpFacade;

    @Test
    public void testGetActualNbpRate() throws Exception {
        //Given
        NbpCurrencyRatesDto[] rates = new NbpCurrencyRatesDto[1];
        rates[0] = new NbpCurrencyRatesDto();
        rates[0].setEffectiveDate("2020-01-02");
        rates[0].setMid(new BigDecimal(4.6));
        NbpCurrencyDto nbpCurrencyDto = new NbpCurrencyDto(1l, "euro", "EUR", rates);
        when(nbpFacade.getNbpCurrency("EUR")).thenReturn(nbpCurrencyDto);

        //When & Then
        mockMvc.perform(get("/v1//NbpCurrency/EUR")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$._id", is(1)))
                .andExpect(jsonPath("$.code", is("EUR")));
    }

    @Test
    public void testgetAllCurrencyList() throws Exception {
        //Given
        List<NbpCurrencyDto> currencyDtoList = new ArrayList<>();
        NbpCurrencyRatesDto[] rates = new NbpCurrencyRatesDto[1];
        rates[0] = new NbpCurrencyRatesDto();
        rates[0].setEffectiveDate("2020-01-02");
        rates[0].setMid(new BigDecimal(4.6));
        NbpCurrencyDto nbpCurrencyDto = new NbpCurrencyDto(1l, "euro", "EUR", rates);
        currencyDtoList.add(nbpCurrencyDto);
        when(nbpFacade.getAllNbpCurrency()).thenReturn(currencyDtoList);

        //When & Then
        mockMvc.perform(get("/v1/NbpCurrency")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0]._id", is(1)))
                .andExpect(jsonPath("$[0].code", is("EUR")));
    }

    @Test
    public void testSaveNbpCurrencyRate() throws Exception {
        //Given
        NbpCurrencyDto currencyDto = new NbpCurrencyDto();
        Gson gson = new Gson();
        String jsonContent = gson.toJson(currencyDto);
        //When & Then
        mockMvc.perform(post("/v1/NbpCurrency")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andDo(print());

        verify(nbpFacade, times(1)).saveNbpCurrency(any());
    }
}