package com.app.kantor.repository;

import com.app.kantor.domain.cart.CartStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface CartStockRepository extends JpaRepository<CartStock, Long> {
    @Override
    List<CartStock> findAll();

    @Override
    CartStock save(CartStock cartStock);

    @Override
    Optional<CartStock> findById(Long aLong);

   // @Override
   // void deleteById(Long aLong);
}
