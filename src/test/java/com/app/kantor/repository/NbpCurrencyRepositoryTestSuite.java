package com.app.kantor.repository;

import com.app.kantor.domain.cart.CartCrypto;
import com.app.kantor.domain.nbp.NbpCurrency;
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
public class NbpCurrencyRepositoryTestSuite {
    @Autowired
    private NbpCurrencyRepository nbpCurrencyRepository;

    @Test
    public void findAll() {
        //Given
        NbpCurrency nbpCurrency = new NbpCurrency();
        //When
        nbpCurrencyRepository.save(nbpCurrency);
        List<NbpCurrency> nbpCurrencyList = nbpCurrencyRepository.findAll();
        //Then
        Assert.assertEquals(1, nbpCurrencyList.size());
        //CleanUp
        nbpCurrencyRepository.deleteById(nbpCurrency.get_id());
        Assert.assertFalse(nbpCurrencyRepository.findById(nbpCurrency.get_id()).isPresent());
    }

    @Test
    public void findById() {
        //Given
          NbpCurrency nbpCurrency = new NbpCurrency();
        //When
        nbpCurrencyRepository.save(nbpCurrency);
        Optional<NbpCurrency> nbpCurrency1 = nbpCurrencyRepository.findById(nbpCurrency.get_id());
        //Then
        Assert.assertTrue(nbpCurrency1.isPresent());
        //CleanUp
        nbpCurrencyRepository.deleteById(nbpCurrency.get_id());
        Assert.assertFalse(nbpCurrencyRepository.findById(nbpCurrency.get_id()).isPresent());
    }
}