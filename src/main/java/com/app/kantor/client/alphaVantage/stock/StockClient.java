package com.app.kantor.client.alphaVantage.stock;

import com.app.kantor.domain.stock.StockDto;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

@Component
public class StockClient {
    public StockDto getActualStockByCode(String endpoit) throws IOException {
        URL url = new URL(endpoit);
        InputStreamReader reader = new InputStreamReader(url.openStream());
        StockDto stockDto = new Gson().fromJson(reader, StockDto.class);
        return stockDto;
    }
}
