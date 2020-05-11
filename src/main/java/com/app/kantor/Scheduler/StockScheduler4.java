package com.app.kantor.Scheduler;

import com.app.kantor.client.alphaVantage.stock.StockCode;
import com.app.kantor.domain.stock.Stock;
import com.app.kantor.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class StockScheduler4 {
    @Autowired
    StockService stockService;


    @Scheduled(fixedDelay = 60000, initialDelay = 50000)//(cron = "0 0 13 * * *", zone = "Europe/Warsaw")
    public void saveStock() throws IOException {
    //    Stock stockABC = stockService.getActualStock(StockCode.AlPHABET.getStockEndpoint());
      // Stock stockNestle = stockService.getActualStock(StockCode.NESTLEADR.getStockEndpoint());
      //  Stock stockLHM = stockService.getActualStock(StockCode.LOCKHEED.getStockEndpoint());
       Stock stockIBM = stockService.getActualStock(StockCode.IBM.getStockEndpoint());
 //       stockService.saveStock(stockABC);
       // stockService.saveStock(stockNestle);
        //stockService.saveStock(stockLHM);
        stockService.saveStock(stockIBM);
        System.out.println("scheduler4 stock");
    }
}
