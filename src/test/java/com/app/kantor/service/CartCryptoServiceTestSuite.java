package com.app.kantor.service;

import com.app.kantor.domain.cart.CartCrypto;
import com.app.kantor.domain.cartproduct.CartCryptoProduct;
import com.app.kantor.domain.crypto.CryptoCurrency;
import com.app.kantor.domain.crypto.CryptoCurrencyDto;
import com.app.kantor.domain.user.User;
import com.app.kantor.exception.CartCryptoNotFoundException;
import com.app.kantor.exception.CartCryptoProductNotFoundException;
import com.app.kantor.exception.CartNbpNotFoundException;
import com.app.kantor.exception.CryptoCurrencyNotFoundException;
import com.app.kantor.repository.CartCryptoProductRepository;
import com.app.kantor.repository.CartCryptoRepository;
import com.app.kantor.repository.CryptoCurrencyRepository;
import com.app.kantor.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CartCryptoServiceTestSuite {

    @Autowired
    CartCryptoRepository cartCryptoRepository;
    @Autowired
    CartCryptoProductRepository cartCryptoProductRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CryptoCurrencyRepository cryptoCurrencyRepository;
    @Autowired
    CartCryptoService cartCryptoService;
    private CartCrypto cartCrypto;
    private CartCryptoProduct cartCryptoProduct;
    private CryptoCurrency cryptoCurrency;
    private User user;
    private List<CartCryptoProduct> cartCryptoProductList;
    private List<CryptoCurrencyDto> cryptoCurrencyDtoList;

    @Test
    public void createCartCryptoTest() {
        //Given
        cryptoCurrency = new CryptoCurrency("BITCOIN", "BTC", "2020-05-05", new BigDecimal(1111));
        user = new User("nick", "password", "sample@mail.com",
                "Name", "Big", false, null, null);

        cartCryptoProductList = new ArrayList<>();
        cartCryptoProduct = new CartCryptoProduct();
        cartCrypto = new CartCrypto();
        cartCryptoProduct.setCartCrypto(cartCrypto);
        cartCryptoProduct.setCryptoCurrency(cryptoCurrency);
        cartCryptoProduct.setAmount(10.0);
        cartCryptoProductList.add(cartCryptoProduct);
        cartCrypto.setCreated("2020-05-05");
        cartCrypto.setUser(user);
        cartCrypto.setCartCryptoProducts(cartCryptoProductList);

        //When
        cartCryptoService.createCartCrypto(cartCrypto);
        //Then
        assertEquals("2020-05-05", cartCryptoRepository.findById(cartCrypto.getCartId()).get().getCreated());
    }

    @Test
    public void deleteCartCryptoTest() {
        //Given
        cryptoCurrency = new CryptoCurrency("BITCOIN", "BTC", "2020-05-05", new BigDecimal(1111));
        user = new User("nick", "password", "sample@mail.com",
                "Name", "Big", false, null, null);

        cartCryptoProductList = new ArrayList<>();
        cartCryptoProduct = new CartCryptoProduct();
        cartCrypto = new CartCrypto();
        cartCryptoProduct.setCartCrypto(cartCrypto);
        cartCryptoProduct.setCryptoCurrency(cryptoCurrency);
        cartCryptoProduct.setAmount(10.0);
        cartCryptoProductList.add(cartCryptoProduct);
        cartCrypto.setCreated("2020-05-05");
        cartCrypto.setUser(user);
        cartCrypto.setCartCryptoProducts(cartCryptoProductList);

        //When
        cartCryptoService.createCartCrypto(cartCrypto);
        //  assertEquals(java.util.Optional.of(1L), java.util.Optional.of(cartCryptoRepository.findById(cartCrypto.getCartId()).get().getCartId()));
        Assert.assertTrue(cartCryptoRepository.findById(cartCrypto.getCartId()).isPresent());
        cartCryptoService.deleteCartCrypto(cartCrypto.getCartId());
        //Then
        Assert.assertFalse(cartCryptoRepository.findById(cartCrypto.getCartId()).isPresent());
    }

    @Test
    public void getCryptoCurrencyFromCartCryptoTest() throws CartCryptoNotFoundException, CartNbpNotFoundException {
        //Given
        CryptoCurrency cryptoCurrency = new CryptoCurrency("BITCOIN", "BTC", "2020-05-05", new BigDecimal(1111));
        User user = new User("nick", "password", "sample@mail.com",
                "Name", "Big", false, null, null);

        List<CartCryptoProduct> cartCryptoProductList = new ArrayList<>();
        CartCryptoProduct cartCryptoProduct = new CartCryptoProduct();
        CartCrypto cartCrypto = new CartCrypto();
        cartCryptoProduct.setCartCrypto(cartCrypto);
        cartCryptoProduct.setCryptoCurrency(cryptoCurrency);
        cartCryptoProduct.setAmount(10.0);
        cartCryptoProductList.add(cartCryptoProduct);
        cartCrypto.setCreated("2020-05-05");
        cartCrypto.setUser(user);
        cartCrypto.setCartCryptoProducts(cartCryptoProductList);
        user.setCartCrypto(cartCrypto);
        userRepository.save(user);
        cryptoCurrencyRepository.save(cryptoCurrency);
        cartCryptoProductRepository.save(cartCryptoProduct);
        cartCryptoRepository.save(cartCrypto);
        //When
        cryptoCurrencyDtoList = cartCryptoService.getCryptoCurrencyFromCartCrypto(cartCrypto.getCartId());
        System.out.println(cryptoCurrencyDtoList.toString());
        //Then
        assertEquals("BTC", cryptoCurrencyDtoList.get(0).realtimeCurrencyExchangeRatedDto.getCode());
    }

    @Test
    public void addCryptoCurrencyToCartCryptoProduct() throws CryptoCurrencyNotFoundException, CartCryptoProductNotFoundException {
        //Given
        CryptoCurrency cryptoCurrency = new CryptoCurrency("BITCOIN", "BTC", "2020-05-05", new BigDecimal(1111));
        User user = new User("nick", "password", "sample@mail.com",
                "Name", "Big", false, null, null);
        cryptoCurrencyRepository.save(cryptoCurrency);
        List<CartCryptoProduct> cartCryptoProductList = new ArrayList<>();
        CartCryptoProduct cartCryptoProduct = new CartCryptoProduct();
        CartCrypto cartCrypto = new CartCrypto();
        cartCryptoProduct.setCartCrypto(cartCrypto);
        cartCryptoProduct.setCryptoCurrency(cryptoCurrency);
        cartCryptoProduct.setAmount(10.0);
        cartCryptoProductList.add(cartCryptoProduct);
        cartCrypto.setCreated("2020-05-05");
        cartCrypto.setUser(user);
        cartCrypto.setCartCryptoProducts(cartCryptoProductList);
        user.setCartCrypto(cartCrypto);
        userRepository.save(user);
        cryptoCurrencyRepository.save(cryptoCurrency);
        cartCryptoRepository.save(cartCrypto);
        cartCryptoProductRepository.save(cartCryptoProduct);
        Long a1 = cartCryptoProductRepository.getOne(cartCryptoProduct.getId()).getId();
        Long a2 = cartCryptoRepository.getOne(cartCrypto.getCartId()).getCartId();
        Long a3 = cryptoCurrencyRepository.getOne(cryptoCurrency.get_id()).get_id();

        //When
        cartCryptoService.addCryptoCurrencyToCartCryptoProduct(a1, a2, a3, 10.5);
        Double amount = cartCryptoProductRepository.getOne(cartCryptoProduct.getId()).getAmount();
        //Then
        assertEquals((Double) 10.5, amount);
    }

    @Test
    public void deleteCartCryptoProductTest() throws CartCryptoProductNotFoundException {
        //Given
        CryptoCurrency cryptoCurrency = new CryptoCurrency("BITCOIN", "BTC", "2020-05-05", new BigDecimal(1111));
        User user = new User("nick", "password", "sample@mail.com",
                "Name", "Big", false, null, null);
        cryptoCurrencyRepository.save(cryptoCurrency);
        List<CartCryptoProduct> cartCryptoProductList = new ArrayList<>();
        CartCryptoProduct cartCryptoProduct = new CartCryptoProduct();
        CartCrypto cartCrypto = new CartCrypto();
        cartCryptoProduct.setCartCrypto(cartCrypto);
        cartCryptoProduct.setCryptoCurrency(cryptoCurrency);
        cartCryptoProduct.setAmount(10.0);
        cartCryptoProductList.add(cartCryptoProduct);
        cartCrypto.setCreated("2020-05-05");
        cartCrypto.setUser(user);
        cartCrypto.setCartCryptoProducts(cartCryptoProductList);
        user.setCartCrypto(cartCrypto);
        userRepository.save(user);
        cryptoCurrencyRepository.save(cryptoCurrency);
        cartCryptoRepository.save(cartCrypto);
        cartCryptoProductRepository.save(cartCryptoProduct);
        //When & Then
        Assert.assertTrue(cartCryptoProductRepository.findById(cartCryptoProduct.getId()).isPresent());
        cartCryptoService.deleteCartCryptoProduct(cartCryptoProduct.getId());
        Assert.assertFalse(cartCryptoProductRepository.findById(cartCryptoProduct.getId()).isPresent());
    }
}
