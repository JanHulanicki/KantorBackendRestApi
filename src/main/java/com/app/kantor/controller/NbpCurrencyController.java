package com.app.kantor.controller;

import com.app.kantor.domain.nbp.NbpCurrencyDto;
import com.app.kantor.facade.valut.NbpFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1")
public class NbpCurrencyController {
    @Autowired
    NbpFacade nbpFacade;

    @GetMapping(value = "/NbpCurrency/{code}")
    public NbpCurrencyDto getActualNbpRate(@PathVariable String code) throws IOException {
        return nbpFacade.getNbpCurrency(code);
    }

    @GetMapping(value = "/NbpCurrency")
    public List<NbpCurrencyDto> getNbpCurrencies() {
        return nbpFacade.getAllNbpCurrency();
    }

    @PostMapping(value = "/NbpCurrency/")
    public void saveNbpRate(@RequestBody NbpCurrencyDto nbpCurrencyDto) {
        nbpFacade.saveNbpCurrency(nbpCurrencyDto);
    }
}
