package com.app.kantor.service;

import com.app.kantor.client.nbp.NbpCurrencyCode;
import com.app.kantor.domain.nbp.NbpCurrency;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
public class NbpServiceApiTest {
    @Mock
    private NbpCurrencyService nbpCurrencyService;
    private NbpCurrency nbpCurrency;

    @Test
    public void getActualNbpRateTest() throws IOException {
        //Given
        nbpCurrency = new NbpCurrency();
        nbpCurrency.setCode("CHF");

        //When
        when(nbpCurrencyService.getActualNdpCurrency(NbpCurrencyCode.CHF.toString())).thenReturn(nbpCurrency);
        nbpCurrency = nbpCurrencyService.getActualNdpCurrency(NbpCurrencyCode.CHF.toString());

        //Then
        assertEquals("CHF", nbpCurrency.getCode());
    }
    @Test
    public void getNbpCurrenciesTest() throws IOException {
        //Given
        nbpCurrency = new NbpCurrency();
        List<NbpCurrency> nbpCurrencies = new ArrayList<>();
        nbpCurrencies.add(nbpCurrency);
        when(nbpCurrencyService.getNbpCurrencies()).thenReturn(nbpCurrencies );

        //When
        nbpCurrencyService.saveNbpCurrency(nbpCurrency);
        nbpCurrencies = nbpCurrencyService.getNbpCurrencies();

        //Then
        assertEquals(1, nbpCurrencies.size());
    }
}
