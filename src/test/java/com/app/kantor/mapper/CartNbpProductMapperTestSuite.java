package com.app.kantor.mapper;

import com.app.kantor.domain.cart.CartNbp;
import com.app.kantor.domain.cartproduct.CartNbpProduct;
import com.app.kantor.domain.cartproduct.CartNbpProductDto;
import com.app.kantor.domain.nbp.NbpCurrency;
import com.app.kantor.domain.user.User;
import com.app.kantor.exception.CartNbpNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@EnableTransactionManagement
@PersistenceContext
@AutoConfigureTestDatabase
public class CartNbpProductMapperTestSuite {
    @Autowired
    CartNbpProductMapper cartNbpProductMapper;

    @Test
    public void mapToCartNbpProductDtoTest() throws CartNbpNotFoundException {
        //Given
        CartNbpProductDto cartNbpProductDto = new CartNbpProductDto();
        CartNbpProduct cartNbpProduct = new CartNbpProduct();
        NbpCurrency nbpCurrency = new NbpCurrency();
        User user = new User();
        CartNbp cartNbp = new CartNbp();
        cartNbp.setUser(user);
        cartNbpProduct.setCartNbp(cartNbp);
        cartNbpProduct.setNbpProduct(nbpCurrency);
        cartNbpProduct.setAmount(2.0);

        //When
        cartNbpProductDto = cartNbpProductMapper.mapToCartNbpProductDto(cartNbpProduct);

        //Then
        assertEquals(java.util.Optional.of(2.0), java.util.Optional.of(cartNbpProductDto.getAmount()));
    }
  }
