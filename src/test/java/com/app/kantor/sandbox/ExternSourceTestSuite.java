package com.app.kantor.sandbox;

import com.app.kantor.client.nbp.NbpCurrenciesClient;
import com.app.kantor.client.nbp.NbpCurrencyCode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExternSourceTestSuite {
    @Autowired
    NbpCurrenciesClient nbpCurrenciesClient;
  //  @Autowired
  //  AlphaVantage alphaVantage;
    @Test
    public void exSourceTest() throws IOException {
        //nbpClientTest.getStreamInfo();
       // alphaVantage.getStreamInfo();
        nbpCurrenciesClient.getActualCurrencyRateByCode(NbpCurrencyCode.CHF.toString());
        nbpCurrenciesClient.getActualCurrencyRateByCode(NbpCurrencyCode.GBP.toString());


    }

}
