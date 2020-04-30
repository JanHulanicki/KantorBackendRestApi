package com.app.kantor.client.alphaVantage.stock;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum StockCode {
    IBM("https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=IBM&apikey=C8WU9BD08FJLFMSD"),
    AlPHABET("https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=GOOG&apikey=C8WU9BD08FJLFMSD"),
    LOCKHEED("https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=LMT&apikey=C8WU9BD08FJLFMSD"),
    NESTLEADR("https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=NSRGY&apikey=C8WU9BD08FJLFMSD");
    private String stockEndpoint;
}
