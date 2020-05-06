package com.app.kantor.repository;

import com.app.kantor.domain.cartproduct.CartNbpProduct;
import com.app.kantor.domain.cartproduct.CartStockProduct;
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
public class CartStockProductRepositoryTestSuite {
    @Autowired
    private CartStockProductRepository cartProductRepository;

    @Test
    public void findAll() {
        //Given
        CartStockProduct cartProduct1 = new CartStockProduct();
        //When
        cartProductRepository.save(cartProduct1);
        List<CartStockProduct> cartProductList = cartProductRepository.findAll();
        //Then
        Assert.assertEquals(1, cartProductList.size());
        //CleanUp
        cartProductRepository.deleteById(cartProduct1.getId());
        Assert.assertFalse(cartProductRepository.findById(cartProduct1.getId()).isPresent());
    }

    @Test
    public void findById() {
        //Given
        CartStockProduct cartProduct1 = new CartStockProduct();
        //When
        cartProductRepository.save(cartProduct1);
        Optional<CartStockProduct> cartProductById = cartProductRepository.findById(cartProduct1.getId());
        //Then
        Assert.assertTrue(cartProductById.isPresent());
        //CleanUp
        cartProductRepository.deleteById(cartProduct1.getId());
        Assert.assertFalse(cartProductRepository.findById(cartProduct1.getId()).isPresent());
    }
}