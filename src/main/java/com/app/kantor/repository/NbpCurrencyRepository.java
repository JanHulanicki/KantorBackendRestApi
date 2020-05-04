package com.app.kantor.repository;

import com.app.kantor.domain.crypto.CryptoCurrency;
import com.app.kantor.domain.nbp.NbpCurrency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NbpCurrencyRepository  extends JpaRepository<NbpCurrency, Long> {
    @Override
    List<NbpCurrency> findAll();

    @Override
    NbpCurrency save(NbpCurrency nbpCurrency);

    @Override
    Optional<NbpCurrency> findById(Long aLong);

    Optional<NbpCurrency> findCryptoCurrencyByCode(String code);
}
