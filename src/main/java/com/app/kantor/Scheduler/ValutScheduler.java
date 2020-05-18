package com.app.kantor.Scheduler;

import com.app.kantor.client.alphaVantage.crypto.CryptoCurrencyCode;
import com.app.kantor.client.nbp.NbpCurrencyCode;
import com.app.kantor.domain.crypto.CryptoCurrency;
import com.app.kantor.domain.nbp.NbpCurrency;
import com.app.kantor.service.CryptoService;
import com.app.kantor.service.NbpCurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class ValutScheduler {
    @Autowired
    CryptoService cryptoService;
    @Autowired
    NbpCurrencyService nbpCurrencyService;

    @Scheduled(cron = "0 0 12 * * *", zone = "Europe/Warsaw")
    public void saveValut() throws IOException {
        String actualDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        NbpCurrency nbpCurrencyEUR = nbpCurrencyService.getActualNdpCurrency(NbpCurrencyCode.EUR.toString());
        NbpCurrency nbpCurrencyCHF = nbpCurrencyService.getActualNdpCurrency(NbpCurrencyCode.CHF.toString());
        NbpCurrency nbpCurrencyGBP = nbpCurrencyService.getActualNdpCurrency(NbpCurrencyCode.GBP.toString());
        NbpCurrency nbpCurrencyUSD = nbpCurrencyService.getActualNdpCurrency(NbpCurrencyCode.USD.toString());
        CryptoCurrency cryptoCurrencyBTC = cryptoService.getActualCryptoRate(CryptoCurrencyCode.BTC.getCryptoEndpoint());
        CryptoCurrency cryptoCurrencyETH = cryptoService.getActualCryptoRate(CryptoCurrencyCode.ETH.getCryptoEndpoint());
        CryptoCurrency cryptoCurrencyLTC = cryptoService.getActualCryptoRate(CryptoCurrencyCode.LTC.getCryptoEndpoint());
        CryptoCurrency cryptoCurrencyXRP = cryptoService.getActualCryptoRate(CryptoCurrencyCode.XRP.getCryptoEndpoint());
        nbpCurrencyService.saveNbpCurrency(nbpCurrencyEUR);
        nbpCurrencyService.saveNbpCurrency(nbpCurrencyCHF);
        nbpCurrencyService.saveNbpCurrency(nbpCurrencyGBP);
        nbpCurrencyService.saveNbpCurrency(nbpCurrencyUSD);
        cryptoService.saveCrypto(cryptoCurrencyBTC);
        cryptoService.saveCrypto(cryptoCurrencyETH);
        cryptoService.saveCrypto(cryptoCurrencyLTC);
        cryptoService.saveCrypto(cryptoCurrencyXRP);
    }
}
