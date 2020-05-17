package com.app.kantor.service;

import com.app.kantor.client.alphaVantage.crypto.CryptoCurrencyCode;
import com.app.kantor.domain.crypto.CryptoCurrency;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@EnableTransactionManagement
@PersistenceContext
@AutoConfigureTestDatabase
public class CryptoServiceApiTest {
    @Mock
    private CryptoService cryptoService;
    private CryptoCurrency cryptoCurrency;

    @Test
    public void getActualCryptoRateTest() throws IOException {
        //Given
        cryptoCurrency = new CryptoCurrency();
        cryptoCurrency.setCode("BTC");

        //When
        when(cryptoService.getActualCryptoRate(CryptoCurrencyCode.BTC.getCryptoEndpoint())).thenReturn(cryptoCurrency);
        cryptoCurrency = cryptoService.getActualCryptoRate(CryptoCurrencyCode.BTC.getCryptoEndpoint());
        // TimeUnit.SECONDS.sleep(10);
        //Then
        assertEquals("BTC", cryptoCurrency.getCode());
    }

    @Test
    public void getAllCryptoTest() throws IOException {
        //Given
        cryptoCurrency = new CryptoCurrency();
        List<CryptoCurrency> cryptoCurrencies = new ArrayList<>();
        cryptoCurrencies.add(cryptoCurrency);
        when(cryptoService.getAllCrypto()).thenReturn(cryptoCurrencies);

        cryptoService.saveCrypto(cryptoCurrency);


        //When
        cryptoCurrencies = cryptoService.getAllCrypto();

        //Then
        assertEquals(1, cryptoCurrencies.size());
    }
}
