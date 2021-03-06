package com.app.kantor.repository;

import com.app.kantor.domain.cartproduct.CartCryptoProduct;
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
    CartCryptoProduct getOne(Long aLong);

    @Override
    void deleteById(Long aLong);
}
