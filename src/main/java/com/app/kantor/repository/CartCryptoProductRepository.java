package com.app.kantor.repository;

import com.app.kantor.domain.cartproduct.CartCryptoProduct;
import com.app.kantor.domain.cartproduct.CartNbpProduct;
import com.app.kantor.domain.crypto.CryptoCurrency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartCryptoProductRepository extends JpaRepository<CartCryptoProduct, Long> {
    @Override
    List<CartCryptoProduct> findAll();

    @Override
    CartCryptoProduct save(CartCryptoProduct cartCryptoProduct);

    @Override
    Optional<CartCryptoProduct> findById(Long aLong);

    @Override
    void deleteById(Long aLong);
}
