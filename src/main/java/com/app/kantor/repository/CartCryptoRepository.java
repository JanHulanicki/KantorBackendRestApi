package com.app.kantor.repository;

import com.app.kantor.domain.cart.CartCrypto;
import com.app.kantor.domain.cart.CartNbp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface CartCryptoRepository extends JpaRepository<CartCrypto, Long> {
    @Override
    List<CartCrypto> findAll();

    @Override
    CartCrypto save(CartCrypto cartCrypto);

    @Override
    Optional<CartCrypto> findById(Long aLong);
}
