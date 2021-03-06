package com.app.kantor.repository;

import com.app.kantor.domain.cart.CartNbp;
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
public class CartNbpRepositoryTestSuite {
    @Autowired
    private CartNbpRepository cartNbpRepository;

    @Test
    public void findAll() {
        //Given
        CartNbp cartNbp = new CartNbp();

        //When
        cartNbpRepository.save(cartNbp);
        List<CartNbp> cartCryptoList = cartNbpRepository.findAll();

        //Then
        Assert.assertEquals(1, cartCryptoList.size());

        //CleanUp
        cartNbpRepository.deleteById(cartNbp.getCartId());
        Assert.assertFalse(cartNbpRepository.findById(cartNbp.getCartId()).isPresent());
    }

    @Test
    public void findById() {
        //Given
        CartNbp cartNbp = new CartNbp();

        //When
        cartNbpRepository.save(cartNbp);
        Optional<CartNbp> cartNbpById = cartNbpRepository.findById(cartNbp.getCartId());

        //Then
        Assert.assertTrue(cartNbpById.isPresent());

        //CleanUp
        cartNbpRepository.deleteById(cartNbp.getCartId());
        Assert.assertFalse(cartNbpRepository.findById(cartNbp.getCartId()).isPresent());
    }
}