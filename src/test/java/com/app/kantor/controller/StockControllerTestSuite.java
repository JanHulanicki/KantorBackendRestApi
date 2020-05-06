package com.app.kantor.controller;

import com.app.kantor.domain.stock.GlobalQuoteDto;
import com.app.kantor.domain.stock.StockDto;
import com.app.kantor.facade.stock.StockFacade;
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
@WebMvcTest(StockController.class)
public class StockControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private StockFacade stockFacade;


    @Test
    public void testGetActualStock() throws Exception {
        //Given
        GlobalQuoteDto quotes = new GlobalQuoteDto();
        quotes.set_id(1L);
        quotes.setSymbol("IBM");
        StockDto stockDto = new StockDto(quotes);
        when(stockFacade.getStock("IBM")).thenReturn(stockDto);
        //When & Then
        mockMvc.perform(get("/v1/stock/IBM")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.globalQuoteDto._id", is(1)))
                .andExpect(jsonPath("$.globalQuoteDto.symbol", is("IBM")));
    }

    @Test
    public void testgetAllStockList() throws Exception {
        //Given
        List<StockDto> stockDtoList = new ArrayList<>();
        GlobalQuoteDto quotes = new GlobalQuoteDto();
        quotes.set_id(1L);
        quotes.setSymbol("IBM");
        StockDto stockDto = new StockDto(quotes);
        stockDtoList.add(stockDto);
        when(stockFacade.getAllStock()).thenReturn(stockDtoList);
        //When & Then
        mockMvc.perform(get("/v1/stock")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.[0].globalQuoteDto._id", is(1)))
                .andExpect(jsonPath("$.[0].globalQuoteDto.symbol", is("IBM")));
    }

    @Test
    public void testSaveStockRate() throws Exception {
        //Given
        StockDto stockDto = new StockDto();
        Gson gson = new Gson();
        String jsonContent = gson.toJson(stockDto);
        //When & Then
        mockMvc.perform(post("/v1/stock")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andDo(print());
        verify(stockFacade, times(1)).saveStock(any());
    }
}