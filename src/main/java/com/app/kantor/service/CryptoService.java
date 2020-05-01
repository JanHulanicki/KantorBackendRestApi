package com.app.kantor.service;

import com.app.kantor.client.alphaVantage.crypto.CryptoClient;
import com.app.kantor.client.alphaVantage.stock.StockClient;
import com.app.kantor.domain.crypto.CryptoCurrency;
import com.app.kantor.domain.stock.Stock;
import com.app.kantor.mapper.CryptoCurrencyMapper;
import com.app.kantor.mapper.StockMapper;
import com.app.kantor.repository.CryptoCurrencyRepository;
import com.app.kantor.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class CryptoService {
    @Autowired
    CryptoCurrencyRepository cryptoCurrencyRepository;
    @Autowired
    CryptoCurrencyMapper cryptoCurrencyMapper;
    @Autowired
    CryptoClient cryptoClient;

    public void saveCrypto(CryptoCurrency cryptoCurrency) {
        cryptoCurrencyRepository.save(cryptoCurrency);
    }

    public CryptoCurrency getActualCryptoRate(String code) throws IOException {
        return
                cryptoCurrencyMapper.mapToCryptoCurrency(cryptoClient.getActualCryptoCurrencyRateByCode(code));
    }

    public List<CryptoCurrency> getAllCrypto() {
        return cryptoCurrencyRepository.findAll();
    }
}
