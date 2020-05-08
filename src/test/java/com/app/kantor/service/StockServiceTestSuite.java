package com.app.kantor.service;

import com.app.kantor.client.alphaVantage.stock.StockCode;
import com.app.kantor.domain.stock.Stock;
import com.app.kantor.repository.StockRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@EnableTransactionManagement
@PersistenceContext
public class StockServiceTestSuite {
    @Autowired
    StockService stockService;
    @Autowired
    StockRepository stockRepository;
    Stock stock;

    @Test
    public void saveStockTest() {
        //Given
        stock = new Stock("Ibm", "IBM", "2020-05-05", new BigDecimal(1111));
        //When
        stockService.saveStock(stock);
        //Then
        assertEquals("Ibm", stockRepository.getOne(stock.get_id()).getStock());
        assertEquals("IBM", stockRepository.getOne(stock.get_id()).getSymbol());
    }

    @Test
    public void getActualStockTest() throws IOException {
        //Given
        stock = new Stock();

        //When
        stock = stockService.getActualStock(StockCode.IBM.getStockEndpoint());

        //Then
        assertEquals("IBM", stock.getSymbol());
    }

    @Test
    public void getNbpCurrenciesTest() throws IOException {
        //Given
        stock = new Stock();
        stock = stock = stockService.getActualStock(StockCode.IBM.getStockEndpoint());
        stockService.saveStock(stock);
        List<Stock> allStock = new ArrayList<>();

        //When
        allStock = stockService.getAllStock();

        //Then
        assertEquals(1, allStock.size());
    }
}
