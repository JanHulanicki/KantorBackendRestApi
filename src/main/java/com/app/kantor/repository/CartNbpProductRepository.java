package com.app.kantor.repository;

import com.app.kantor.domain.cartproduct.CartNbpProduct;
import com.app.kantor.domain.nbp.NbpCurrency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartNbpProductRepository extends JpaRepository<CartNbpProduct, Long> {
    @Override
    List<CartNbpProduct> findAll();

    @Override
    CartNbpProduct save(CartNbpProduct cartNbpProduct);

    @Override
    Optional<CartNbpProduct> findById(Long aLong);

    @Override
    void deleteById(Long aLong);
}
