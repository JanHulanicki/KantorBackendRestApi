package com.app.kantor.facade.valut;

import com.app.kantor.domain.crypto.CryptoCurrencyDto;
import com.app.kantor.mapper.CryptoCurrencyMapper;
import com.app.kantor.service.CryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class CryptoFacade {
    @Autowired
    CryptoService cryptoService;
    @Autowired
    CryptoCurrencyMapper cryptoCurrencyMapper;

    public CryptoCurrencyDto getCryptoCurrency(String code) throws IOException {
        return cryptoCurrencyMapper.mapToCryptoCurrencyDto(cryptoService.getActualCryptoRate(code));
    }

    public List<CryptoCurrencyDto> getAllCryptoCurrency() {
        return cryptoCurrencyMapper.mapToCryptoCurrencyDtoList(cryptoService.getAllCrypto());

    }

    public void saveCryptoCurrency(CryptoCurrencyDto cryptoCurrencyDto) {
        cryptoService.saveCrypto(cryptoCurrencyMapper.mapToCryptoCurrency(cryptoCurrencyDto));
    }
}
