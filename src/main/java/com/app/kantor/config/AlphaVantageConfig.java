package com.app.kantor.config;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class AlphaVantageConfig {
    private String cryptoApiEndpoint="https://www.alphavantage.co/query?function=CURRENCY_EXCHANGE_RATE&from_currency=";///?format=json";
}
