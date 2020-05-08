package com.app.kantor.repository;

import com.app.kantor.domain.stock.Stock;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@EnableTransactionManagement
public class StockRepositoryTestSuite {
    @Autowired
    private StockRepository stockRepository;

    @Test
    public void findAll() {
        //Given
        Stock stock = new Stock();

        //When
        stockRepository.save(stock);
        List<Stock> stocks = stockRepository.findAll();

        //Then
        Assert.assertEquals(1, stocks.size());

        //CleanUp
        stockRepository.deleteById(stock.get_id());
        Assert.assertFalse(stockRepository.findById(stock.get_id()).isPresent());
    }

    @Test
    public void findById() {
        //Given
        Stock stock = new Stock();

        //When
        stockRepository.save(stock);
        Optional<Stock> stockById = stockRepository.findById(stock.get_id());

        //Then
        Assert.assertTrue(stockById.isPresent());

        //CleanUp
        stockRepository.deleteById(stock.get_id());
        Assert.assertFalse(stockRepository.findById(stock.get_id()).isPresent());
    }
}