package com.app.kantor.service;

import com.app.kantor.domain.cart.CartNbp;
import com.app.kantor.domain.cartproduct.CartNbpProduct;
import com.app.kantor.domain.nbp.NbpCurrency;
import com.app.kantor.domain.nbp.NbpCurrencyDto;
import com.app.kantor.domain.user.User;
import com.app.kantor.exception.*;
import com.app.kantor.repository.CartNbpProductRepository;
import com.app.kantor.repository.CartNbpRepository;
import com.app.kantor.repository.NbpCurrencyRepository;
import com.app.kantor.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@EnableTransactionManagement
@PersistenceContext
@AutoConfigureTestDatabase
public class CartNbpServiceTestSuite {

    @Autowired
    CartNbpRepository cartNbpRepository;
    @Autowired
    CartNbpProductRepository cartNbpProductRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    NbpCurrencyRepository nbpCurrencyRepository;
    @Autowired
    CartNbpService cartNbpService;
    private CartNbp cartNbp;
    private CartNbpProduct cartNbpProduct;
    private NbpCurrency nbpCurrency;
    private User user;
    private List<CartNbpProduct> cartNbpProductList;
    private List<NbpCurrencyDto> nbpCurrencyDtoList;

    @Test
    public void createCartNbpTest() {
        //Given
        nbpCurrency = new NbpCurrency("USDOLAR", "USD", "2020-05-05", new BigDecimal(1111));
        user = new User("nick", "password", "sample@mail.com",
                "Name", "Big", false, null, null);

        cartNbpProductList = new ArrayList<>();
        cartNbpProduct = new CartNbpProduct();
        cartNbp = new CartNbp();
        cartNbpProduct.setCartNbp(cartNbp);
        cartNbpProduct.setNbpProduct(nbpCurrency);
        cartNbpProduct.setAmount(10.0);
        cartNbpProductList.add(cartNbpProduct);
        cartNbp.setCreated("2020-05-05");
        cartNbp.setUser(user);
        cartNbp.setCartNbpProducts(cartNbpProductList);

        //When
        cartNbpService.createCartNbp(cartNbp);
        //Then
        assertEquals("2020-05-05", cartNbpRepository.findById(cartNbp.getCartId()).get().getCreated());
    }

    @Test
    public void deleteCartNbpTest() {
        //Given
        nbpCurrency = new NbpCurrency("USDOLAR", "USD", "2020-05-05", new BigDecimal(1111));
        user = new User("nick", "password", "sample@mail.com",
                "Name", "Big", false, null, null);

        cartNbpProductList = new ArrayList<>();
        cartNbpProduct = new CartNbpProduct();
        cartNbp = new CartNbp();
        cartNbpProduct.setCartNbp(cartNbp);
        cartNbpProduct.setNbpProduct(nbpCurrency);
        cartNbpProduct.setAmount(10.0);
        cartNbpProductList.add(cartNbpProduct);
        cartNbp.setCreated("2020-05-05");
        cartNbp.setUser(user);
        cartNbp.setCartNbpProducts(cartNbpProductList);

        //When
        cartNbpService.createCartNbp(cartNbp);
        Assert.assertTrue(cartNbpRepository.findById(cartNbp.getCartId()).isPresent());
        cartNbpService.deleteCartNbp(cartNbp.getCartId());

        //Then
        Assert.assertFalse(cartNbpRepository.findById(cartNbp.getCartId()).isPresent());
    }

    @Test
    public void getNbpCurrencyFromCartNbpTest() throws CartNbpNotFoundException, CartNbpProductNotFoundException {
        //Given
        nbpCurrency = new NbpCurrency("USDOLAR", "USD", "2020-05-05", new BigDecimal(1111));
        user = new User("nick", "password", "sample@mail.com",
                "Name", "Big", false, null, null);

        List<CartNbpProduct> cartNbpProductList = new ArrayList<>();
        CartNbpProduct cartNbpProduct = new CartNbpProduct();
        CartNbp cartNbp = new CartNbp();
        cartNbpProduct.setCartNbp(cartNbp);
        cartNbpProduct.setNbpProduct(nbpCurrency);
        cartNbpProduct.setAmount(10.0);
        cartNbpProductList.add(cartNbpProduct);
        cartNbp.setCreated("2020-05-05");
        cartNbp.setUser(user);
        cartNbp.setCartNbpProducts(cartNbpProductList);
        user.setCartNbp(cartNbp);
        userRepository.save(user);
        nbpCurrencyRepository.save(nbpCurrency);
        cartNbpProductRepository.save(cartNbpProduct);
        cartNbpRepository.save(cartNbp);

        //When
        nbpCurrencyDtoList = cartNbpService.getNbpCurrencyFromCartNbp(cartNbp.getCartId());
        System.out.println(nbpCurrencyDtoList.toString());

        //Then
        assertEquals("USD", nbpCurrencyDtoList.get(0).getCode());
    }

    @Test
    public void addCryptoCurrencyToCartCryptoProduct() throws CryptoCurrencyNotFoundException, CartCryptoProductNotFoundException, NbpCurrencyNotFoundException, CartNbpProductNotFoundException {
        //Given
        nbpCurrency = new NbpCurrency("USDOLAR", "USD", "2020-05-05", new BigDecimal(1111));
        user = new User("nick", "password", "sample@mail.com",
                "Name", "Big", false, null, null);
        nbpCurrencyRepository.save(nbpCurrency);
        List<CartNbpProduct> cartNbpProductList = new ArrayList<>();
        CartNbpProduct cartNbpProduct = new CartNbpProduct();
        CartNbp cartNbp = new CartNbp();
        cartNbpProduct.setCartNbp(cartNbp);
        cartNbpProduct.setNbpProduct(nbpCurrency);
        cartNbpProduct.setAmount(10.0);
        cartNbpProductList.add(cartNbpProduct);
        cartNbp.setCreated("2020-05-05");
        cartNbp.setUser(user);
        cartNbp.setCartNbpProducts(cartNbpProductList);
        user.setCartNbp(cartNbp);
        userRepository.save(user);
        nbpCurrencyRepository.save(nbpCurrency);
        cartNbpRepository.save(cartNbp);
        cartNbpProductRepository.save(cartNbpProduct);
        Long a1 = cartNbpProductRepository.getOne(cartNbpProduct.getId()).getId();
        Long a2 = cartNbpRepository.getOne(cartNbp.getCartId()).getCartId();
        Long a3 = nbpCurrencyRepository.getOne(nbpCurrency.get_id()).get_id();

        //When
        cartNbpService.addNbpCurrencyToCartNbpProduct(a1, a2, a3, 10.5);
        Double amount = cartNbpProductRepository.getOne(cartNbpProduct.getId()).getAmount();

        //Then
        assertEquals((Double) 10.5, amount);
    }

    @Test
    public void deleteCartCryptoProductTest() throws CartCryptoProductNotFoundException, CartNbpProductNotFoundException {
        //Given
        nbpCurrency = new NbpCurrency("USDOLAR", "USD", "2020-05-05", new BigDecimal(1111));
        user = new User("nick", "password", "sample@mail.com",
                "Name", "Big", false, null, null);
        nbpCurrencyRepository.save(nbpCurrency);
        List<CartNbpProduct> cartNbpProductList = new ArrayList<>();
        CartNbpProduct cartNbpProduct = new CartNbpProduct();
        CartNbp cartNbp = new CartNbp();
        cartNbpProduct.setCartNbp(cartNbp);
        cartNbpProduct.setNbpProduct(nbpCurrency);
        cartNbpProduct.setAmount(10.0);
        cartNbpProductList.add(cartNbpProduct);
        cartNbp.setCreated("2020-05-05");
        cartNbp.setUser(user);
        cartNbp.setCartNbpProducts(cartNbpProductList);
        user.setCartNbp(cartNbp);
        userRepository.save(user);
        nbpCurrencyRepository.save(nbpCurrency);
        cartNbpRepository.save(cartNbp);
        cartNbpProductRepository.save(cartNbpProduct);
        //When & Then
        Assert.assertTrue(cartNbpProductRepository.findById(cartNbpProduct.getId()).isPresent());
        cartNbpService.deleteCartNbpProduct(cartNbpProduct.getId());
        Assert.assertFalse(cartNbpProductRepository.findById(cartNbpProduct.getId()).isPresent());
    }
}
