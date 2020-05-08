package com.app.kantor.repository;

import com.app.kantor.domain.cart.CartStock;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@EnableTransactionManagement
public class CartStockRepositoryTestSuite {
    @Autowired
    private CartStockRepository cartStockRepository;

    @Test
    public void findAll() {
        //Given
        CartStock cartStock = new CartStock();

        //When
        cartStockRepository.save(cartStock);
        List<CartStock> cartCryptoList = cartStockRepository.findAll();

        //Then
        Assert.assertEquals(1, cartCryptoList.size());

        //CleanUp
        cartStockRepository.deleteById(cartStock.getCartId());
        Assert.assertFalse(cartStockRepository.findById(cartStock.getCartId()).isPresent());
    }

    @Test
    public void findById() {
        //Given
        CartStock cartStock = new CartStock();

        //When
        cartStockRepository.save(cartStock);
        Optional<CartStock> cartNbpById = cartStockRepository.findById(cartStock.getCartId());

        //Then
        Assert.assertTrue(cartNbpById.isPresent());

        //CleanUp
        cartStockRepository.deleteById(cartStock.getCartId());
        Assert.assertFalse(cartStockRepository.findById(cartStock.getCartId()).isPresent());
    }
}