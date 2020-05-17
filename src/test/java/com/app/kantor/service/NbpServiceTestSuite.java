package com.app.kantor.service;

import com.app.kantor.client.nbp.NbpCurrencyCode;
import com.app.kantor.domain.nbp.NbpCurrency;
import com.app.kantor.repository.NbpCurrencyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
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
@AutoConfigureTestDatabase
public class NbpServiceTestSuite {
    @Autowired
    NbpCurrencyService nbpCurrencyService;
    @Autowired
    NbpCurrencyRepository nbpCurrencyRepository;
    NbpCurrency nbpCurrency;

    @Test
    public void saveNbpCurrencyTest() {
        //Given
        nbpCurrency = new NbpCurrency("USDOLAR", "USD", "2020-05-05", new BigDecimal(1111));

        //When
        nbpCurrencyService.saveNbpCurrency(nbpCurrency);

        //Then
        assertEquals("USDOLAR", nbpCurrencyRepository.getOne(nbpCurrency.get_id()).getCurrency());
        assertEquals("USD", nbpCurrencyRepository.getOne(nbpCurrency.get_id()).getCode());
    }


}
