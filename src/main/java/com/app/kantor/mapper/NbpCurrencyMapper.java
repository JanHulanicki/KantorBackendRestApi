package com.app.kantor.mapper;

import com.app.kantor.domain.nbp.NbpCurrency;
import com.app.kantor.domain.nbp.NbpCurrencyDto;
import com.app.kantor.domain.nbp.NbpCurrencyRatesDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NbpCurrencyMapper {
    public NbpCurrency mapToNbpCurrency(final NbpCurrencyDto nbpCurrencyDto) {
        NbpCurrency nbpCurrency = new NbpCurrency();
        nbpCurrency.set_id(nbpCurrencyDto.get_id());
        nbpCurrency.setCurrency(nbpCurrencyDto.getCurrency());
        nbpCurrency.setCode(nbpCurrencyDto.getCode());
        nbpCurrency.setDate(nbpCurrencyDto.getRates()[0].getEffectiveDate());
        nbpCurrency.setMid(nbpCurrencyDto.getRates()[0].getMid());
        return nbpCurrency;
    }

    public NbpCurrencyDto mapToNbpCurrencyDto(final NbpCurrency nbpCurrency) {
        return new NbpCurrencyDto(
                nbpCurrency.get_id(),
                nbpCurrency.getCurrency(),
                nbpCurrency.getCode(),
                maptoNbpCurrencyRatesDtoArr(nbpCurrency));
    }

    public NbpCurrencyRatesDto[] maptoNbpCurrencyRatesDtoArr(NbpCurrency nbpCurrency) {
        NbpCurrencyRatesDto[] currencyRatesDto = new NbpCurrencyRatesDto[1];
        currencyRatesDto[0] = new NbpCurrencyRatesDto();
        currencyRatesDto[0].setEffectiveDate(nbpCurrency.getDate());
        currencyRatesDto[0].setMid(nbpCurrency.getMid());
        return currencyRatesDto;
    }

    public List<NbpCurrencyDto> mapToNbpCurrencyDtoList(List<NbpCurrency> nbpCurrencies) {
        List<NbpCurrencyDto> nbpCurrencyDtoList = nbpCurrencies.stream()
                .map(i -> new NbpCurrencyDto(
                        i.get_id(),
                        i.getCurrency(),
                        i.getCode(),
                        maptoNbpCurrencyRatesDtoArr(i))
                ).collect(Collectors.toList());
        return nbpCurrencyDtoList;
    }
}
