package com.app.kantor.config;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class NbpConfig {
    private String nbpApiEndpoint = "https://api.nbp.pl/api/exchangerates/rates/a/";
}
