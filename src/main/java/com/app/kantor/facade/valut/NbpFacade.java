package com.app.kantor.facade.valut;

import com.app.kantor.domain.nbp.NbpCurrencyDto;
import com.app.kantor.mapper.NbpCurrencyMapper;
import com.app.kantor.service.NbpCurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class NbpFacade {
    @Autowired
    NbpCurrencyService nbpCurrencyService;
    @Autowired
    NbpCurrencyMapper nbpCurrencyMapper;

    public NbpCurrencyDto getNbpCurrency(String code) throws IOException {
        return nbpCurrencyMapper.mapToNbpCurrencyDto(nbpCurrencyService.getActualNdpCurrency(code));
    }

    public List<NbpCurrencyDto> getAllNbpCurrency() {
        return nbpCurrencyMapper.mapToNbpCurrencyDtoList(nbpCurrencyService.getNbpCurrencies());
    }

    public void saveNbpCurrency(NbpCurrencyDto nbpCurrencyDto) {
        nbpCurrencyService.saveNbpCurrency(nbpCurrencyMapper.mapToNbpCurrency(nbpCurrencyDto));
    }
}
