package com.app.kantor.client.alphaVantage.crypto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
@NoArgsConstructor
@AllArgsConstructor
@Getter
//@Component
public enum CryptoCurrencyCode {
    BTC("https://www.alphavantage.co/query?function=CURRENCY_EXCHANGE_RATE&from_currency=BTC&to_currency=PLN&apikey=C8WU9BD08FJLFMSD"),
    ETH("https://www.alphavantage.co/query?function=CURRENCY_EXCHANGE_RATE&from_currency=ETH&to_currency=PLN&apikey=C8WU9BD08FJLFMSD"),
    XRP("https://www.alphavantage.co/query?function=CURRENCY_EXCHANGE_RATE&from_currency=XRP&to_currency=PLN&apikey=C8WU9BD08FJLFMSD"),
    LTC("https://www.alphavantage.co/query?function=CURRENCY_EXCHANGE_RATE&from_currency=LTC&to_currency=PLN&apikey=C8WU9BD08FJLFMSD");
    private String cryptoEndpoint;
}
