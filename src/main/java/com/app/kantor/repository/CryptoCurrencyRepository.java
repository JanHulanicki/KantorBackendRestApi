package com.app.kantor.repository;

import com.app.kantor.domain.crypto.CryptoCurrency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CryptoCurrencyRepository extends JpaRepository<CryptoCurrency, Long> {
    @Override
    List<CryptoCurrency> findAll();

    @Override
    CryptoCurrency save(CryptoCurrency cryptoCurrency);

    @Override
    Optional<CryptoCurrency> findById(Long aLong);

    @Override
    CryptoCurrency getOne(Long aLong);
}
