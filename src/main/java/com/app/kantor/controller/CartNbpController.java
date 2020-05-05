package com.app.kantor.controller;

import com.app.kantor.domain.cart.CartNbpDto;
import com.app.kantor.domain.nbp.NbpCurrencyDto;
import com.app.kantor.exception.CartNbpNotFoundException;
import com.app.kantor.exception.CartNbpProductNotFoundException;
import com.app.kantor.exception.NbpCurrencyNotFoundException;
import com.app.kantor.exception.UserNotFoundException;
import com.app.kantor.mapper.CartNbpMapper;
import com.app.kantor.service.CartNbpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1")
public class CartNbpController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CartNbpController.class);
    @Autowired
    CartNbpService cartNbpService;
    @Autowired
    CartNbpMapper cartNbpMapper;

    @PostMapping(value = "/nbpcart")
    public void createCart(@RequestBody CartNbpDto cartNbpDto) throws UserNotFoundException {
        LOGGER.info("Creating new cart");
        cartNbpService.createCartNbp(cartNbpMapper.mapToCartNbp(cartNbpDto));
    }

    @DeleteMapping(value = "/nbpcart/{cartId}")
    public void deleteCart(@PathVariable Long cartId) {
        LOGGER.info("Deleting the cart");
        cartNbpService.deleteCartNbp(cartId);
    }

    @GetMapping(value = "/nbpcurrency/{cartId}")
    public List<NbpCurrencyDto> getProducts(@PathVariable Long cartId) throws CartNbpNotFoundException, CartNbpProductNotFoundException {
        return cartNbpService.getNbpCurrencyFromCartNbp(cartId);
    }

    @PostMapping(value = "/nbpcurrency/{cartProductId}/{cartNbpId}/{productId}/{amount}")
    public void addProduct(@PathVariable Long cartProductId, @PathVariable Long cartNbpId, @PathVariable Long productId, @PathVariable Double amount) throws NbpCurrencyNotFoundException, CartNbpProductNotFoundException {
        LOGGER.info("Adding a product to the cart");
        cartNbpService.addNbpCurrencyToCartNbpProduct(cartProductId, cartNbpId, productId, amount);
    }

    @DeleteMapping(value = "/nbpcurrency/{cartProductId}")
    public void deleteProduct(@PathVariable Long cartProductId) throws CartNbpProductNotFoundException {
        LOGGER.info("Deleting a product from the cart");
        cartNbpService.deleteCartNbpProduct(cartProductId);
    }
}