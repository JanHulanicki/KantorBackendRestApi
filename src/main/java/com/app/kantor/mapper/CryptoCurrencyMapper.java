package com.app.kantor.mapper;

import com.app.kantor.domain.crypto.CryptoCurrency;
import com.app.kantor.domain.crypto.CryptoCurrencyDto;
import com.app.kantor.domain.crypto.RealtimeCurrencyExchangeRatedDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CryptoCurrencyMapper {
    public CryptoCurrency mapToCryptoCurrency(final CryptoCurrencyDto cryptoCurrencyDto) {
        CryptoCurrency cryptoCurrency = new CryptoCurrency();
        cryptoCurrency.set_id(cryptoCurrencyDto.getRealtimeCurrencyExchangeRatedDto().get_id());
        cryptoCurrency.setCurrency(cryptoCurrencyDto.getRealtimeCurrencyExchangeRatedDto().getCurrency());
        cryptoCurrency.setCode(cryptoCurrencyDto.getRealtimeCurrencyExchangeRatedDto().getCode());
        cryptoCurrency.setDate(cryptoCurrencyDto.getRealtimeCurrencyExchangeRatedDto().getDate());
        cryptoCurrency.setMid(cryptoCurrencyDto.getRealtimeCurrencyExchangeRatedDto().getMid());
        return cryptoCurrency;
    }

    public CryptoCurrencyDto mapToCryptoCurrencyDto(CryptoCurrency cryptoCurrency) {
        CryptoCurrencyDto cryptoCurrencyDto = new CryptoCurrencyDto();
        RealtimeCurrencyExchangeRatedDto realtimeCurrencyExchangeRatedDto = new RealtimeCurrencyExchangeRatedDto();
        realtimeCurrencyExchangeRatedDto.set_id(cryptoCurrency.get_id());
        realtimeCurrencyExchangeRatedDto.setCurrency(cryptoCurrency.getCurrency());
        realtimeCurrencyExchangeRatedDto.setCode(cryptoCurrency.getCode());
        realtimeCurrencyExchangeRatedDto.setDate(cryptoCurrency.getDate());
        realtimeCurrencyExchangeRatedDto.setMid(cryptoCurrency.getMid());
        cryptoCurrencyDto.setRealtimeCurrencyExchangeRatedDto(realtimeCurrencyExchangeRatedDto);
        return cryptoCurrencyDto;
    }

    public RealtimeCurrencyExchangeRatedDto maptoCryptoCurrencyRatesDto(CryptoCurrency cryptoCurrency) {
        RealtimeCurrencyExchangeRatedDto realtimeCurrencyExchangeRatedDto = new RealtimeCurrencyExchangeRatedDto();
        realtimeCurrencyExchangeRatedDto.set_id(cryptoCurrency.get_id());
        realtimeCurrencyExchangeRatedDto.setCode(cryptoCurrency.getCode());
        realtimeCurrencyExchangeRatedDto.setCurrency(cryptoCurrency.getCurrency());
        realtimeCurrencyExchangeRatedDto.setMid(cryptoCurrency.getMid());
        realtimeCurrencyExchangeRatedDto.setDate(cryptoCurrency.getDate());

        return realtimeCurrencyExchangeRatedDto;
    }

    public List<CryptoCurrencyDto> mapToCryptoCurrencyDtoList(List<CryptoCurrency> cryptoCurrencies) {
        List<CryptoCurrencyDto> cryptoCurrencyDtos = cryptoCurrencies.stream()
                .map(i -> new CryptoCurrencyDto(
                        maptoCryptoCurrencyRatesDto(i))).collect(Collectors.toList());
        return cryptoCurrencyDtos;
    }
}
