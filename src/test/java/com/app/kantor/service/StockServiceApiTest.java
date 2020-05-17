package com.app.kantor.service;

import com.app.kantor.client.alphaVantage.stock.StockCode;
import com.app.kantor.domain.stock.Stock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@EnableTransactionManagement
@PersistenceContext
@AutoConfigureTestDatabase
public class StockServiceApiTest {
    @Mock
    StockService stockService;
    Stock stock;

    @Test
    public void getActualStockTest() throws IOException {
        //Given
        stock = new Stock();
        stock.setSymbol("IBM");
        //When
        when(stockService.getActualStock(StockCode.IBM.getStockEndpoint())).thenReturn(stock);
        stock = stockService.getActualStock(StockCode.IBM.getStockEndpoint());

        //Then
        assertEquals("IBM", stock.getSymbol());
    }
    @Test
    public void getAllStockTest() throws IOException {
        //Given
        stock = new Stock();
        List<Stock> allStock = new ArrayList<>();
        allStock.add(stock);
      //  stock = stock = stockService.getActualStock(StockCode.IBM.getStockEndpoint());
        stockService.saveStock(stock);
        when(stockService.getAllStock()).thenReturn(allStock);

        //When
        allStock = stockService.getAllStock();

        //Then
        assertEquals(1, allStock.size());
    }
}
