package com.app.kantor.mapper;

import com.app.kantor.domain.crypto.CryptoCurrency;
import com.app.kantor.domain.crypto.CryptoCurrencyDto;
import com.app.kantor.domain.crypto.RealtimeCurrencyExchangeRatedDto;
import com.app.kantor.domain.stock.GlobalQuoteDto;
import com.app.kantor.domain.stock.Stock;
import com.app.kantor.domain.stock.StockDto;
import org.springframework.stereotype.Component;

@Component
public class StockMapper {
    private Stock mapToStock(final StockDto stockDto) {
        Stock stock = new Stock();
        stock.set_id(stockDto.getGlobalQuoteDto().get_id());
        stock.setSymbol(stockDto.getGlobalQuoteDto().getSymbol());
        stock.setOpen(stockDto.getGlobalQuoteDto().getOpen());
        stock.setHigh(stockDto.getGlobalQuoteDto().getHigh());
        stock.setLow(stockDto.getGlobalQuoteDto().getLow());
        stock.setPrice(stockDto.getGlobalQuoteDto().getPrice());
        stock.setVolume(stockDto.getGlobalQuoteDto().getVolume());
        stock.setLatestTradingDay(stockDto.getGlobalQuoteDto().getLatestTradingDay());
        stock.setPreviousClose(stockDto.getGlobalQuoteDto().getPreviousClose());
        stock.setChange(stockDto.getGlobalQuoteDto().getChange());
        return stock;
    }

    private StockDto mapToStockDto(final Stock stock) {
        StockDto stockDto =new StockDto();
        GlobalQuoteDto globalQuoteDto= new GlobalQuoteDto();
        globalQuoteDto.set_id(stock.get_id());
        globalQuoteDto.setSymbol(stock.getSymbol());
        globalQuoteDto.setOpen(stock.getOpen());
        globalQuoteDto.setHigh(stock.getHigh());
        globalQuoteDto.setLow(stock.getLow());
        globalQuoteDto.setPrice(stock.getPrice());
        globalQuoteDto.setVolume(stock.getVolume());
        globalQuoteDto.setLatestTradingDay(stock.getLatestTradingDay());
        globalQuoteDto.setPreviousClose(stock.getPreviousClose());
        globalQuoteDto.setChange(stock.getChange());
       stockDto.setGlobalQuoteDto( globalQuoteDto);
        return stockDto;
    }
}
