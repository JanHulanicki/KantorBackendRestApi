package com.app.kantor.service;

import com.app.kantor.client.alphaVantage.stock.StockCode;
import com.app.kantor.domain.stock.Stock;
import com.app.kantor.repository.StockRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@EnableTransactionManagement
@PersistenceContext
@AutoConfigureTestDatabase
public class StockServiceTestSuite {
    @Autowired
    StockService stockService;
    @Autowired
    StockRepository stockRepository;
    Stock stock;

    @Test
    public void saveStockTest() throws InterruptedException {
        //Given
        stock = new Stock("Ibm", "IBM", "2020-05-05", new BigDecimal(1111));
        //When
        stockService.saveStock(stock);
        //Then
        assertEquals("Ibm", stockRepository.getOne(stock.get_id()).getStock());
        assertEquals("IBM", stockRepository.getOne(stock.get_id()).getSymbol());
        TimeUnit.SECONDS.sleep(10);
    }


}
