package com.app.kantor.service;

import com.app.kantor.domain.cart.CartCrypto;
import com.app.kantor.domain.cartproduct.CartCryptoProduct;
import com.app.kantor.domain.cartproduct.CartNbpProduct;
import com.app.kantor.domain.crypto.CryptoCurrency;
import com.app.kantor.domain.crypto.CryptoCurrencyDto;
import com.app.kantor.exception.*;
import com.app.kantor.mapper.CryptoCurrencyMapper;
import com.app.kantor.repository.CartCryptoProductRepository;
import com.app.kantor.repository.CartCryptoRepository;
import com.app.kantor.repository.CryptoCurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartCryptoService {
    @Autowired
    CartCryptoRepository cartCryptoRepository;
    @Autowired
    CartCryptoProductRepository cartCryptoProductRepository;
    @Autowired
    CryptoCurrencyRepository cryptoCurrencyRepository;
    @Autowired
    CryptoCurrencyMapper cryptoCurrencyMapper;
   // @Autowired
   // CartCryptoRepository cartCryptoRepository;
    public CartCrypto createCartCrypto(final CartCrypto cartCrypto) {
        return cartCryptoRepository.save(cartCrypto);
    }

    public void deleteCartCrypto(final Long cartId) {
        cartCryptoRepository.deleteById(cartId);
    }


    public List<CryptoCurrencyDto> getCryptoCurrencyFromCartCrypto(final Long cartId) throws  CartNbpNotFoundException, CartCryptoNotFoundException {
        if (cartCryptoRepository.findById(cartId).isPresent()) {
            CartCrypto cartCrypto = cartCryptoRepository.findById(cartId).orElseThrow(CartCryptoNotFoundException::new);
            List<CryptoCurrency> cryptoCurrencyList = cartCrypto.getCartCryptoProducts().stream()
                    .map(CartCryptoProduct::getCryptoCurrency).collect(Collectors.toList());
            return cryptoCurrencyMapper.mapToCryptoCurrencyDtoList(cryptoCurrencyList);
        } else {
            throw new CartNbpNotFoundException();
        }
    }

    public void addCryptoCurrencyToCartCryptoProduct(final Long cartCryptoProductsId,final Long cartCryptoId, final Long cryptoCurrencytId, Double amount) throws  CartCryptoProductNotFoundException, CryptoCurrencyNotFoundException {
        if (cartCryptoProductRepository.findById(cartCryptoProductsId).isPresent()) {
            if (cryptoCurrencyRepository.findById(cryptoCurrencytId).isPresent()) {
                CryptoCurrency cryptoCurrency = new CryptoCurrency(cryptoCurrencyRepository.findById(cryptoCurrencytId).get().getCurrency(),
                        cryptoCurrencyRepository.findById(cryptoCurrencytId).get().getCode(),
                        cryptoCurrencyRepository.findById(cryptoCurrencytId).get().getDate(),
                        cryptoCurrencyRepository.findById(cryptoCurrencytId).get().getMid());
                CartCryptoProduct cartCryptoProduct= new CartCryptoProduct(cartCryptoProductsId,cartCryptoRepository.findById(cartCryptoId).get(),cryptoCurrencyRepository.findById(cryptoCurrencytId).get(),amount);
                cartCryptoProductRepository.save(cartCryptoProduct);
            } else {
                throw new CryptoCurrencyNotFoundException();
            }
        } else {
            throw new CartCryptoProductNotFoundException();
        }
    }

    public void deleteCartCryptoProduct(final Long cartCryptoProductId) throws CartCryptoProductNotFoundException {
        if (cartCryptoProductRepository.findById(cartCryptoProductId).isPresent()) {
            CartCryptoProduct cartCryptoProduct = cartCryptoProductRepository.findById(cartCryptoProductId).get();
            cartCryptoProductRepository.deleteById(cartCryptoProduct.getId());
        } else {
            throw new CartCryptoProductNotFoundException();
        }
    }
}

