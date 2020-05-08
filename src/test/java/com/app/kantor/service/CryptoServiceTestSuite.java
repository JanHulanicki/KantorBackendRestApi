package com.app.kantor.service;

import com.app.kantor.client.alphaVantage.crypto.CryptoCurrencyCode;
import com.app.kantor.domain.crypto.CryptoCurrency;
import com.app.kantor.repository.CryptoCurrencyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@EnableTransactionManagement
@PersistenceContext
public class CryptoServiceTestSuite {
    @Autowired
    CryptoService cryptoService;
    @Autowired
    CryptoCurrencyRepository cryptoCurrencyRepository;
    CryptoCurrency cryptoCurrency;

    @Test
    public void saveCryptoTest() {
        //Given
        cryptoCurrency = new CryptoCurrency("BITCOIN", "BTC", "2020-05-05", new BigDecimal(1111));

        //When
        cryptoService.saveCrypto(cryptoCurrency);

        //Then
        assertEquals("BITCOIN", cryptoCurrencyRepository.getOne(cryptoCurrency.get_id()).getCurrency());
        assertEquals("BTC", cryptoCurrencyRepository.getOne(cryptoCurrency.get_id()).getCode());
    }

    @Test
    public void getActualCryptoRateTest() throws IOException {
        //Given
        cryptoCurrency = new CryptoCurrency();

        //When
        cryptoCurrency = cryptoService.getActualCryptoRate(CryptoCurrencyCode.BTC.getCryptoEndpoint());

        //Then
        assertEquals("BTC", cryptoCurrency.getCode());
    }

    @Test
    public void getAllCryptoTest() throws IOException {
        //Given
        cryptoCurrency = new CryptoCurrency();
        cryptoCurrency = cryptoService.getActualCryptoRate(CryptoCurrencyCode.BTC.getCryptoEndpoint());
        cryptoService.saveCrypto(cryptoCurrency);
        List<CryptoCurrency> cryptoCurrencies = new ArrayList<>();

        //When
        cryptoCurrencies = cryptoService.getAllCrypto();

        //Then
        assertEquals(1, cryptoCurrencies.size());
    }
}
