package com.app.kantor.repository;

import com.app.kantor.domain.crypto.CryptoCurrency;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@EnableTransactionManagement
public class CryptoCurrencyRepositoryTestSuite {
    @Autowired
    private CryptoCurrencyRepository cryptoCurrencyRepository;

    @Test
    public void findAll() {
        //Given
        CryptoCurrency cryptoCurrency = new CryptoCurrency();

        //When
        cryptoCurrencyRepository.save(cryptoCurrency);
        List<CryptoCurrency> cryptoCurrencyList = cryptoCurrencyRepository.findAll();

        //Then
        Assert.assertEquals(1, cryptoCurrencyList.size());

        //CleanUp
        cryptoCurrencyRepository.deleteById(cryptoCurrency.get_id());
        Assert.assertFalse(cryptoCurrencyRepository.findById(cryptoCurrency.get_id()).isPresent());
    }

    @Test
    public void findById() {
        //Given
        CryptoCurrency cryptoCurrency = new CryptoCurrency();

        //When
        cryptoCurrencyRepository.save(cryptoCurrency);
        Optional<CryptoCurrency> cartProductById = cryptoCurrencyRepository.findById(cryptoCurrency.get_id());

        //Then
        Assert.assertTrue(cartProductById.isPresent());

        //CleanUp
        cryptoCurrencyRepository.deleteById(cryptoCurrency.get_id());
        Assert.assertFalse(cryptoCurrencyRepository.findById(cryptoCurrency.get_id()).isPresent());
    }
}