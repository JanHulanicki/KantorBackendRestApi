package com.app.kantor.repository;

import com.app.kantor.domain.cart.CartStock;
import com.app.kantor.domain.cartproduct.CartCryptoProduct;
import com.app.kantor.domain.cartproduct.CartStockProduct;
import com.app.kantor.domain.stock.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartStockProductRepository extends JpaRepository<CartStockProduct, Long> {
    @Override
    List<CartStockProduct> findAll();

    @Override
    CartStockProduct save(CartStockProduct cartStockProduct);

    @Override
    Optional<CartStockProduct> findById(Long aLong);

    @Override
    void deleteById(Long aLong);
}
