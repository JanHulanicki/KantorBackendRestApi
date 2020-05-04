package com.app.kantor.repository;

import com.app.kantor.domain.cart.CartNbp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface CartNbpRepository extends JpaRepository<CartNbp, Long> {
    @Override
    List<CartNbp> findAll();

    @Override
    CartNbp save(CartNbp cartNbp);

    @Override
    Optional<CartNbp> findById(Long aLong);
}
