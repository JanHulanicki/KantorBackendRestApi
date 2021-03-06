package com.app.kantor.repository;

import com.app.kantor.domain.stock.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {
    @Override
    List<Stock> findAll();

    @Override
    Stock save(Stock stock);

    @Override
    Optional<Stock> findById(Long aLong);

    @Override
    Stock getOne(Long aLong);

    Optional<Stock> findCryptoCurrencyByCode(String code);
}
