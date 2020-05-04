package com.app.kantor.controller;

import com.app.kantor.domain.crypto.CryptoCurrencyDto;
import com.app.kantor.domain.stock.StockDto;
import com.app.kantor.facade.stock.StockFacade;
import com.app.kantor.facade.valut.CryptoFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1")
public class StockController {
    @Autowired
    StockFacade stockFacade;

    @GetMapping(value = "/stock/{code}")
    public StockDto getActualStockRate(@PathVariable String code) throws IOException {
        return stockFacade.getStock(code);
    }

    @GetMapping(value = "/stock")
    public List<StockDto> getAllStock() {
        return stockFacade.getAllStock();
    }

    @PostMapping(value = "/stock/")
    public void saveStockRate(@RequestBody StockDto stockDto) {
        stockFacade.saveStock(stockDto);
    }
}
