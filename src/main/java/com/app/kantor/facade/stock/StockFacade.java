package com.app.kantor.facade.stock;

import com.app.kantor.domain.stock.StockDto;
import com.app.kantor.mapper.StockMapper;
import com.app.kantor.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class StockFacade {
    @Autowired
    StockService stockService;
    @Autowired
    StockMapper stockMapper;

    public StockDto getStock(String code) throws IOException {
        return stockMapper.mapToStockDto(stockService.getActualStock(code));
    }

    public List<StockDto> getAllStock() {
        return stockMapper.mapToStockDtoList(stockService.getAllStock());

    }

    public void saveStock(StockDto stockDto) {
        stockService.saveStock(stockMapper.mapToStock(stockDto));
    }
}
