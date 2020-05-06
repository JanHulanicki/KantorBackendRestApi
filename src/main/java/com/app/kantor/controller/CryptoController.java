package com.app.kantor.controller;

import com.app.kantor.domain.crypto.CryptoCurrencyDto;
import com.app.kantor.facade.valut.CryptoFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1")
public class CryptoController {
    @Autowired
    CryptoFacade cryptoFacade;

    @GetMapping(value = "/crypto/{code}")
    public CryptoCurrencyDto getActualCryptoRate(@PathVariable String code) throws IOException {
        return cryptoFacade.getCryptoCurrency(code);
    }

    @GetMapping(value = "/crypto")
    public List<CryptoCurrencyDto> gerCryptoCurrencies() {
        return cryptoFacade.getAllCryptoCurrency();
    }

    @PostMapping(value = "/crypto")
    public void saveCryptoRate(@RequestBody CryptoCurrencyDto cryptoCurrencyDto) {
        cryptoFacade.saveCryptoCurrency(cryptoCurrencyDto);
    }
}
