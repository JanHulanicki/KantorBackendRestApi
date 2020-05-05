package com.app.kantor.controller;

import com.app.kantor.domain.cart.CartStockDto;
import com.app.kantor.domain.stock.StockDto;
import com.app.kantor.exception.CartStockNotFoundException;
import com.app.kantor.exception.CartStockProductNotFoundException;
import com.app.kantor.exception.StockNotFoundException;
import com.app.kantor.exception.UserNotFoundException;
import com.app.kantor.mapper.CartStockMapper;
import com.app.kantor.service.CartStockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1")
public class CartStockController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CartStockController.class);
    @Autowired
    CartStockService cartStockService;
    @Autowired
    CartStockMapper cartStockMapper;

    @PostMapping(value = "/stockcart")
    public void createCart(@RequestBody CartStockDto cartStockDto) throws UserNotFoundException {
        LOGGER.info("Creating new cart");
        cartStockService.createCartStock(cartStockMapper.mapToCartStock(cartStockDto));
    }

    @DeleteMapping(value = "/stockcart/{cartId}")
    public void deleteCart(@PathVariable Long cartId) {
        LOGGER.info("Deleting the cart");
        cartStockService.deleteCartStock(cartId);
    }

    @GetMapping(value = "/stockprodcart/{cartId}")
    public List<StockDto> getProducts(@PathVariable Long cartId) throws CartStockNotFoundException {
        return cartStockService.getStockFromCartStock(cartId);
    }

    @PostMapping(value = "/stockprodcart/{cartProductId}/{cartCryptoId}/{productId}/{amount}")
    public void addProduct(@PathVariable Long cartProductId, @PathVariable Long cartCryptoId, @PathVariable Long productId, @PathVariable Double amount) throws CartStockProductNotFoundException, StockNotFoundException {
        LOGGER.info("Adding a product to the cart");
        cartStockService.addStockToCartStockProduct(cartProductId, cartCryptoId, productId, amount);
    }

    @DeleteMapping(value = "/stockprodcart/{cartProductId}")
    public void deleteProduct(@PathVariable Long cartProductId) throws CartStockProductNotFoundException {
        LOGGER.info("Deleting a product from the cart");
        cartStockService.deleteCartStockProduct(cartProductId);
    }
}