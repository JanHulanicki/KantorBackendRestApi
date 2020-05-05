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

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1")
public class CartNbpController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CartNbpController.class);
    @Autowired
    CartNbpService cartNbpService;
    @Autowired
    CartNbpMapper cartNbpMapper;

   // @RequestMapping(method = RequestMethod.POST, value = "createCart", consumes = APPLICATION_JSON_VALUE)
   @PostMapping(value = "/nbpCart")
    public void createCart(@RequestBody CartNbpDto cartNbpDto) throws UserNotFoundException {
        LOGGER.info("Creating new cart");
        cartNbpService.createCartNbp(cartNbpMapper.mapToCartNbp(cartNbpDto));
    }

    //@RequestMapping(method = RequestMethod.DELETE, value = "deleteCart")
   @DeleteMapping(value="/nbpcart/{cartId}")
    public void deleteCart(@PathVariable Long cartId) {
        LOGGER.info("Deleting the cart");
        cartNbpService.deleteCartNbp(cartId);
    }

   // @RequestMapping(method = RequestMethod.GET, value = "getProducts")
    @GetMapping(value="/nbpcurrency/{cartId}")
    public List<NbpCurrencyDto> getProducts(@PathVariable Long cartId) throws CartNbpNotFoundException, CartNbpProductNotFoundException {
        return cartNbpService.getNbpCurrencyFromCartNbp(cartId);
    }

   // @RequestMapping(method = RequestMethod.POST, value = "addProduct", consumes = APPLICATION_JSON_VALUE)
    @PostMapping(value= "/nbpcurrency/{cartProductId}/{cartNbpId}/{productId}/{amount}")
    public void addProduct(@PathVariable Long cartProductId, @PathVariable Long cartNbpId, @PathVariable Long productId, @PathVariable Double amount) throws NbpCurrencyNotFoundException, CartNbpProductNotFoundException {
        LOGGER.info("Adding a product to the cart");
        cartNbpService.addNbpCurrencyToCartNbpProduct(cartProductId, cartNbpId, productId, amount);
    }

    //@RequestMapping(method = RequestMethod.DELETE, value = "deleteProduct")
    @DeleteMapping(value="/nbpcurrency/{cartProductId}")
    public void deleteProduct(@RequestParam Long cartProductId) throws CartNbpProductNotFoundException {
        LOGGER.info("Deleting a product from the cart");
        cartNbpService.deleteCartNbpProduct(cartProductId);
    }
}
