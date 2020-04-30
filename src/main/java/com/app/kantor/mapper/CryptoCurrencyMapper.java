package com.app.kantor.mapper;

import com.app.kantor.domain.crypto.CryptoCurrency;
import com.app.kantor.domain.crypto.CryptoCurrencyDto;
import com.app.kantor.domain.crypto.RealtimeCurrencyExchangeRatedDto;
import com.app.kantor.domain.nbp.NbpCurrency;
import com.app.kantor.domain.nbp.NbpCurrencyDto;
import org.springframework.stereotype.Component;

@Component
public class CryptoCurrencyMapper {
    private CryptoCurrency mapToCryptoCurrency(final CryptoCurrencyDto cryptoCurrencyDto) {
        CryptoCurrency cryptoCurrency = new CryptoCurrency();
        cryptoCurrency.setId(cryptoCurrencyDto.getRealtimeCurrencyExchangeRatedDto().getId());
        cryptoCurrency.setCurrency(cryptoCurrencyDto.getRealtimeCurrencyExchangeRatedDto().getCurrency());
        cryptoCurrency.setCode(cryptoCurrencyDto.getRealtimeCurrencyExchangeRatedDto().getCode());
        cryptoCurrency.setDate(cryptoCurrencyDto.getRealtimeCurrencyExchangeRatedDto().getDate());
        cryptoCurrency.setMid(cryptoCurrencyDto.getRealtimeCurrencyExchangeRatedDto().getMid());
        return cryptoCurrency;
    }

    private CryptoCurrencyDto mapToCryptoCurrencyDto(final CryptoCurrency cryptoCurrency) {
        CryptoCurrencyDto cryptoCurrencyDto =new CryptoCurrencyDto();
        RealtimeCurrencyExchangeRatedDto realtimeCurrencyExchangeRatedDto= new RealtimeCurrencyExchangeRatedDto();
        realtimeCurrencyExchangeRatedDto.setId(cryptoCurrency.getId());
        realtimeCurrencyExchangeRatedDto.setCurrency(cryptoCurrency.getCurrency());
        realtimeCurrencyExchangeRatedDto.setCode(cryptoCurrency.getCode());
        realtimeCurrencyExchangeRatedDto.setDate(cryptoCurrency.getDate());
        realtimeCurrencyExchangeRatedDto.setMid(cryptoCurrency.getMid());
        cryptoCurrencyDto.setRealtimeCurrencyExchangeRatedDto(realtimeCurrencyExchangeRatedDto);
        return cryptoCurrencyDto;
    }
}
