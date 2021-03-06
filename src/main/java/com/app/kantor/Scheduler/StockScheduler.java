package com.app.kantor.Scheduler;

import com.app.kantor.client.alphaVantage.stock.StockCode;
import com.app.kantor.domain.stock.Stock;
import com.app.kantor.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class StockScheduler {
    @Autowired
    StockService stockService;

    @Scheduled(cron = "0 0 13 * * *", zone = "Europe/Warsaw")
    public void saveStock() throws IOException {
        Stock stockABC = stockService.getActualStock(StockCode.AlPHABET.getStockEndpoint());
        stockService.saveStock(stockABC);
    }
}
