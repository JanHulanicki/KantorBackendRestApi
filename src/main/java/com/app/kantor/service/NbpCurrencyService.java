package com.app.kantor.service;

import com.app.kantor.client.nbp.NbpCurrenciesClient;
import com.app.kantor.domain.nbp.NbpCurrency;
import com.app.kantor.mapper.NbpCurrencyMapper;
import com.app.kantor.repository.NbpCurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class NbpCurrencyService {
    @Autowired
    NbpCurrencyRepository nbpCurrencyRepository;
    @Autowired
    NbpCurrencyMapper nbpCurrencyMapper;
    @Autowired
    NbpCurrenciesClient nbpCurrenciesClient;

    public void saveNbpCurrency(NbpCurrency nbpCurrency) {
        nbpCurrencyRepository.save(nbpCurrency);
    }

    public NbpCurrency getActualNdpCurrency(String code) throws IOException {
        return
                nbpCurrencyMapper.mapToNbpCurrency(nbpCurrenciesClient.getActualCurrencyRateByCode(code));

    }

    public List<NbpCurrency> getNbpCurrencies() {
        return nbpCurrencyRepository.findAll();
    }
}
