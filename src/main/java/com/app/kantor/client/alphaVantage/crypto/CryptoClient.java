package com.app.kantor.client.alphaVantage.crypto;

import com.app.kantor.config.NbpConfig;
import com.app.kantor.domain.crypto.CryptoCurrencyDto;
import com.app.kantor.domain.nbp.NbpCurrencyDto;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
@Component
public class CryptoClient {
    @Autowired
    NbpConfig nbpConfig;
//CryptoCurrencyDto
    public void getActualCryptoCurrencyRateByCode(String endpoit) throws IOException {
        // URL url = new URL("https://api.nbp.pl/api/exchangerates/rates/a/chf/?format=json");
        URL url = new URL(endpoit);
        InputStreamReader reader = new InputStreamReader(url.openStream());
        CryptoCurrencyDto cryptoCurrencyDto = new Gson().fromJson(reader,CryptoCurrencyDto.class);
        System.out.println(cryptoCurrencyDto.toString());
       // return nbpCurrencyDto;
    }
}
