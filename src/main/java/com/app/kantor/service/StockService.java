package com.app.kantor.service;

import com.app.kantor.client.alphaVantage.stock.StockClient;
import com.app.kantor.domain.stock.Stock;
import com.app.kantor.mapper.StockMapper;
import com.app.kantor.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class StockService {
    @Autowired
    StockRepository stockRepository;
    @Autowired
    StockMapper stockMapper;
    @Autowired
    StockClient stockClient;

    public void saveStock(Stock stock) {
        stockRepository.save(stock);
    }

    public Stock getActualStock(String code) throws IOException {
        return
                stockMapper.mapToStock(stockClient.getActualStockByCode(code));
    }

    public List<Stock> getAllStock() {
        return stockRepository.findAll();
    }
}
