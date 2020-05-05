package com.app.kantor.controller;

import com.app.kantor.domain.cart.CartCryptoDto;
import com.app.kantor.domain.crypto.CryptoCurrencyDto;
import com.app.kantor.exception.*;
import com.app.kantor.mapper.CartCryptoMapper;
import com.app.kantor.service.CartCryptoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1")
public class CartCryptoController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CartCryptoController.class);
    @Autowired
    CartCryptoService cartCryptoService;
    @Autowired
    CartCryptoMapper cartCryptoMapper;

    @PostMapping(value = "/cryptocart")
    public void createCart(@RequestBody CartCryptoDto cartCryptoDto) throws UserNotFoundException {
        LOGGER.info("Creating new cart");
        cartCryptoService.createCartCrypto(cartCryptoMapper.mapToCartCrypto(cartCryptoDto));
    }

    @DeleteMapping(value = "/cryptocart/{cartId}")
    public void deleteCart(@PathVariable Long cartId) {
        LOGGER.info("Deleting the cart");
        cartCryptoService.deleteCartCrypto(cartId);
    }

    @GetMapping(value = "/cryptocurrency/{cartId}")
    public List<CryptoCurrencyDto> getProducts(@PathVariable Long cartId) throws CartNbpNotFoundException, CartNbpProductNotFoundException, CartCryptoNotFoundException {
        return cartCryptoService.getCryptoCurrencyFromCartCrypto(cartId);
    }

    @PostMapping(value = "/cryptocurrency/{cartProductId}/{cartCryptoId}/{productId}/{amount}")
    public void addProduct(@PathVariable Long cartProductId, @PathVariable Long cartCryptoId, @PathVariable Long productId, @PathVariable Double amount) throws CryptoCurrencyNotFoundException, CartCryptoProductNotFoundException {
        LOGGER.info("Adding a product to the cart");
        cartCryptoService.addCryptoCurrencyToCartCryptoProduct(cartProductId, cartCryptoId, productId, amount);
    }

    @DeleteMapping(value = "/cryptocurrency/{cartProductId}")
    public void deleteProduct(@PathVariable Long cartProductId) throws CartNbpProductNotFoundException, CartCryptoProductNotFoundException {
        LOGGER.info("Deleting a product from the cart");
        cartCryptoService.deleteCartCryptoProduct(cartProductId);
    }
}