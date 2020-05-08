package com.app.kantor.controller;

import com.app.kantor.domain.cart.CartStockDto;
import com.app.kantor.domain.stock.GlobalQuoteDto;
import com.app.kantor.domain.stock.StockDto;
import com.app.kantor.mapper.CartStockMapper;
import com.app.kantor.service.CartStockService;
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
@WebMvcTest(CartStockController.class)
public class CartStockControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CartStockService cartStockService;
    @MockBean
    private CartStockMapper cartStockMapper;

    @Test
    public void testCreateCryptoCart() throws Exception {
        //Given
        CartStockDto cartStockDto = new CartStockDto(1L, "2000-12-14", 2L);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(cartStockDto);

        //When & Then
        mockMvc.perform(post("/v1/stockcart")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andDo(print());

        verify(cartStockService, times(1)).createCartStock(cartStockMapper.mapToCartStock(any()));
    }

    @Test
    public void testDeleteStockCart() throws Exception {
        //Given & When & Then
        mockMvc.perform(delete("/v1/stockcart/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetStockList() throws Exception {
        //Given
        List<StockDto> stockDtoList = new ArrayList<>();
        GlobalQuoteDto globalQuoteDto = new GlobalQuoteDto();
        globalQuoteDto.set_id(1L);
        globalQuoteDto.setSymbol("IBM");
        globalQuoteDto.setOpen(new BigDecimal(100));
        globalQuoteDto.setHigh(new BigDecimal(101));
        globalQuoteDto.setLow(new BigDecimal(101));
        StockDto stockDto = new StockDto(globalQuoteDto);
        stockDtoList.add(stockDto);
        when(cartStockService.getStockFromCartStock(1l)).thenReturn(stockDtoList);

        //When & Then
        mockMvc.perform(get("/v1/stockprodcart/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.[0].globalQuoteDto._id", is(1)))
                .andExpect(jsonPath("$.[0].globalQuoteDto.symbol", is("IBM")));
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
        mockMvc.perform(post("/v1/stockprodcart/1/2/3/2.0")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andDo(print());
        verify(cartStockService, times(1)).addStockToCartStockProduct(1L, 2L, 3L, 2.0);
    }

    @Test
    public void testDeleteProductFromCartProduct() throws Exception {
        //Given & When & Then
        mockMvc.perform(delete("/v1/stockprodcart/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }
}