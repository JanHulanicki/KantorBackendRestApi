package com.app.kantor.service;

import com.app.kantor.domain.cart.CartStock;
import com.app.kantor.domain.cartproduct.CartStockProduct;
import com.app.kantor.domain.stock.Stock;
import com.app.kantor.domain.stock.StockDto;
import com.app.kantor.domain.user.User;
import com.app.kantor.exception.CartCryptoProductNotFoundException;
import com.app.kantor.exception.CartStockNotFoundException;
import com.app.kantor.exception.CartStockProductNotFoundException;
import com.app.kantor.exception.StockNotFoundException;
import com.app.kantor.repository.CartStockProductRepository;
import com.app.kantor.repository.CartStockRepository;
import com.app.kantor.repository.StockRepository;
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
public class CartStockServiceTestSuite {

    @Autowired
    CartStockRepository cartStockRepository;
    @Autowired
    CartStockProductRepository cartStockProductRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    StockRepository stockCurrencyRepository;
    @Autowired
    CartStockService cartStockService;
    private CartStock cartStock;
    private CartStockProduct cartStockProduct;
    private Stock stock;
    private User user;
    private List<CartStockProduct> cartStockProductList;
    private List<StockDto> stockDtoList;

    @Test
    public void createCartStockTest() {
        //Given
        stock = new Stock("Ibm", "IBM", "2020-05-05", new BigDecimal(1111));
        user = new User("nick", "password", "sample@mail.com",
                "Name", "Big", false, null, null);

        cartStockProductList = new ArrayList<>();
        cartStockProduct = new CartStockProduct();
        cartStock = new CartStock();
        cartStockProduct.setCartStock(cartStock);
        cartStockProduct.setStockProduct(stock);
        cartStockProduct.setAmount(10.0);
        cartStockProductList.add(cartStockProduct);
        cartStock.setCreated("2020-05-05");
        cartStock.setUser(user);
        cartStock.setCartStockProducts(cartStockProductList);

        //When
        cartStockService.createCartStock(cartStock);

        //Then
        assertEquals("2020-05-05", cartStockRepository.findById(cartStock.getCartId()).get().getCreated());
    }

    @Test
    public void deleteCartStockTest() {
        //Given
        stock = new Stock("Ibm", "IBM", "2020-05-05", new BigDecimal(1111));
        user = new User("nick", "password", "sample@mail.com",
                "Name", "Big", false, null, null);

        cartStockProductList = new ArrayList<>();
        cartStockProduct = new CartStockProduct();
        cartStock = new CartStock();
        cartStockProduct.setCartStock(cartStock);
        cartStockProduct.setStockProduct(stock);
        cartStockProduct.setAmount(10.0);
        cartStockProductList.add(cartStockProduct);
        cartStock.setCreated("2020-05-05");
        cartStock.setUser(user);
        cartStock.setCartStockProducts(cartStockProductList);

        //When
        cartStockService.createCartStock(cartStock);
        Assert.assertTrue(cartStockRepository.findById(cartStock.getCartId()).isPresent());
        cartStockService.deleteCartStock(cartStock.getCartId());

        //Then
        Assert.assertFalse(cartStockRepository.findById(cartStock.getCartId()).isPresent());
    }

    @Test
    public void getStockCurrencyFromCartStockTest() throws CartStockNotFoundException, CartStockProductNotFoundException {
        //Given
        stock = new Stock("Ibm", "IBM", "2020-05-05", new BigDecimal(1111));
        user = new User("nick", "password", "sample@mail.com",
                "Name", "Big", false, null, null);

        List<CartStockProduct> cartStockProductList = new ArrayList<>();
        CartStockProduct cartStockProduct = new CartStockProduct();
        CartStock cartStock = new CartStock();
        cartStockProduct.setCartStock(cartStock);
        cartStockProduct.setStockProduct(stock);
        cartStockProduct.setAmount(10.0);
        cartStockProductList.add(cartStockProduct);
        cartStock.setCreated("2020-05-05");
        cartStock.setUser(user);
        cartStock.setCartStockProducts(cartStockProductList);
        user.setCartStock(cartStock);
        userRepository.save(user);
        stockCurrencyRepository.save(stock);
        cartStockProductRepository.save(cartStockProduct);
        cartStockRepository.save(cartStock);

        //When
        stockDtoList = cartStockService.getStockFromCartStock(cartStock.getCartId());

        //Then
        assertEquals("IBM", stockDtoList.get(0).getGlobalQuoteDto().getSymbol());
    }

    @Test
    public void addCryptoCurrencyToCartCryptoProduct() throws CartStockProductNotFoundException, StockNotFoundException {
        //Given
        stock = new Stock("Ibm", "IBM", "2020-05-05", new BigDecimal(1111));
        user = new User("nick", "password", "sample@mail.com",
                "Name", "Big", false, null, null);
        stockCurrencyRepository.save(stock);
        List<CartStockProduct> cartStockProductList = new ArrayList<>();
        CartStockProduct cartStockProduct = new CartStockProduct();
        CartStock cartStock = new CartStock();
        cartStockProduct.setCartStock(cartStock);
        cartStockProduct.setStockProduct(stock);
        cartStockProduct.setAmount(10.0);
        cartStockProductList.add(cartStockProduct);
        cartStock.setCreated("2020-05-05");
        cartStock.setUser(user);
        cartStock.setCartStockProducts(cartStockProductList);
        user.setCartStock(cartStock);
        userRepository.save(user);
        stockCurrencyRepository.save(stock);
        cartStockRepository.save(cartStock);
        cartStockProductRepository.save(cartStockProduct);
        Long a1 = cartStockProductRepository.getOne(cartStockProduct.getId()).getId();
        Long a2 = cartStockRepository.getOne(cartStock.getCartId()).getCartId();
        Long a3 = stockCurrencyRepository.getOne(stock.get_id()).get_id();

        //When
        cartStockService.addStockToCartStockProduct(a1, a2, a3, 10.5);
        Double amount = cartStockProductRepository.getOne(cartStockProduct.getId()).getAmount();

        //Then
        assertEquals((Double) 10.5, amount);
    }

    @Test
    public void deleteCartCryptoProductTest() throws CartCryptoProductNotFoundException, CartStockProductNotFoundException {
        //Given
        stock = new Stock("Ibm", "IBM", "2020-05-05", new BigDecimal(1111));
        user = new User("nick", "password", "sample@mail.com",
                "Name", "Big", false, null, null);
        stockCurrencyRepository.save(stock);
        List<CartStockProduct> cartStockProductList = new ArrayList<>();
        CartStockProduct cartStockProduct = new CartStockProduct();
        CartStock cartStock = new CartStock();
        cartStockProduct.setCartStock(cartStock);
        cartStockProduct.setStockProduct(stock);
        cartStockProduct.setAmount(10.0);
        cartStockProductList.add(cartStockProduct);
        cartStock.setCreated("2020-05-05");
        cartStock.setUser(user);
        cartStock.setCartStockProducts(cartStockProductList);
        user.setCartStock(cartStock);
        userRepository.save(user);
        stockCurrencyRepository.save(stock);
        cartStockRepository.save(cartStock);
        cartStockProductRepository.save(cartStockProduct);

        //When & Then
        Assert.assertTrue(cartStockProductRepository.findById(cartStockProduct.getId()).isPresent());
        cartStockService.deleteCartStockProduct(cartStockProduct.getId());
        Assert.assertFalse(cartStockProductRepository.findById(cartStockProduct.getId()).isPresent());
    }
}
