package com.app.kantor.service;

import com.app.kantor.domain.crypto.CryptoCurrency;
import com.app.kantor.repository.CryptoCurrencyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@EnableTransactionManagement
@PersistenceContext
@AutoConfigureTestDatabase
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
}
