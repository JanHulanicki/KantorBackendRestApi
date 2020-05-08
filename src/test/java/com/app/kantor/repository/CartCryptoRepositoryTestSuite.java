package com.app.kantor.repository;

import com.app.kantor.domain.cart.CartCrypto;
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
public class CartCryptoRepositoryTestSuite {
    @Autowired
    private CartCryptoRepository cartCryptoRepository;

    @Test
    public void findAll() {
        //Given
        CartCrypto cartCrypto = new CartCrypto();

        //When
        cartCryptoRepository.save(cartCrypto);
        List<CartCrypto> cartCryptoList = cartCryptoRepository.findAll();

        //Then
        Assert.assertEquals(1, cartCryptoList.size());

        //CleanUp
        cartCryptoRepository.deleteById(cartCrypto.getCartId());
        Assert.assertFalse(cartCryptoRepository.findById(cartCrypto.getCartId()).isPresent());
    }

    @Test
    public void findById() {
        //Given
        CartCrypto cartCrypto = new CartCrypto();

        //When
        cartCryptoRepository.save(cartCrypto);
        Optional<CartCrypto> cartProductById = cartCryptoRepository.findById(cartCrypto.getCartId());

        //Then
        Assert.assertTrue(cartProductById.isPresent());

        //CleanUp
        cartCryptoRepository.deleteById(cartCrypto.getCartId());
        Assert.assertFalse(cartCryptoRepository.findById(cartCrypto.getCartId()).isPresent());
    }
}