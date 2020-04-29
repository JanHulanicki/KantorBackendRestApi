package com.app.kantor.client.nbp;

import com.app.kantor.config.NbpConfig;
import com.app.kantor.domain.NbpCurrencyDto;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
@Component
public class NbpCurrenciesClient {
    @Autowired
    NbpConfig nbpConfig;

     public NbpCurrencyDto getActualCurrencyRateByCode() throws IOException {
        // URL url = new URL("https://api.nbp.pl/api/exchangerates/rates/a/chf/?format=json");
         URL url = new URL(nbpConfig.getNbpApiEndpoint());
         InputStreamReader reader = new InputStreamReader(url.openStream());
         NbpCurrencyDto nbpCurrencyDto= new Gson().fromJson(reader,NbpCurrencyDto.class);
         System.out.println(nbpCurrencyDto.toString());
         return nbpCurrencyDto;
    }

}
