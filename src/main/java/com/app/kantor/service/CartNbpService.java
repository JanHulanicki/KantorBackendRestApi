package com.app.kantor.service;

import com.app.kantor.domain.cart.CartNbp;
import com.app.kantor.domain.cartproduct.CartNbpProduct;
import com.app.kantor.domain.nbp.NbpCurrency;
import com.app.kantor.domain.nbp.NbpCurrencyDto;
import com.app.kantor.exception.CartNbpNotFoundException;
import com.app.kantor.exception.CartNbpProductNotFoundException;
import com.app.kantor.exception.NbpCurrencyNotFoundException;
import com.app.kantor.mapper.NbpCurrencyMapper;
import com.app.kantor.repository.CartNbpProductRepository;
import com.app.kantor.repository.CartNbpRepository;
import com.app.kantor.repository.NbpCurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartNbpService {
    @Autowired
    CartNbpRepository cartNbpRepository;
    @Autowired
    CartNbpProductRepository cartNbpProductRepository;
    @Autowired
    NbpCurrencyRepository nbpCurrencyRepository;
    @Autowired
    NbpCurrencyMapper nbpCurrencyMapper;

    public CartNbp createCartNbp(final CartNbp cartNbp) {
        return cartNbpRepository.save(cartNbp);
    }

    public void deleteCartNbp(final Long cartId) {
        cartNbpRepository.deleteById(cartId);
    }


    public List<NbpCurrencyDto> getNbpCurrencyFromCartNbp(final Long cartId) throws CartNbpProductNotFoundException, CartNbpNotFoundException {
        if (cartNbpRepository.findById(cartId).isPresent()) {
            CartNbp cartNbp = cartNbpRepository.findById(cartId).orElseThrow(CartNbpNotFoundException::new);
            List<NbpCurrency> nbpCurrencyList = cartNbp.getCartNbpProducts().stream()
                    .map(CartNbpProduct::getNbpProduct).collect(Collectors.toList());
            return nbpCurrencyMapper.mapToNbpCurrencyDtoList(nbpCurrencyList);
        } else {
            throw new CartNbpNotFoundException();
        }
    }

    public void addNbpCurrencyToCartNbpProduct(final Long cartNbpProductsId,final Long cartNbpId, final Long nbpCurrencytId, Double amount) throws CartNbpProductNotFoundException, NbpCurrencyNotFoundException {
        if (cartNbpProductRepository.findById(cartNbpProductsId).isPresent()) {
            if (nbpCurrencyRepository.findById(nbpCurrencytId).isPresent()) {
                NbpCurrency nbpCurrency = new NbpCurrency(nbpCurrencyRepository.findById(nbpCurrencytId).get().getCurrency(),
                        nbpCurrencyRepository.findById(nbpCurrencytId).get().getCode(),
                        nbpCurrencyRepository.findById(nbpCurrencytId).get().getDate(),
                        nbpCurrencyRepository.findById(nbpCurrencytId).get().getMid());
                CartNbpProduct cartNbpProduct= new CartNbpProduct(cartNbpProductsId,cartNbpRepository.findById(cartNbpId).get(),nbpCurrencyRepository.findById(nbpCurrencytId).get(),amount);
                cartNbpProductRepository.save(cartNbpProduct);
            } else {
                throw new NbpCurrencyNotFoundException();
            }
        } else {
            throw new CartNbpProductNotFoundException();
        }
    }

    public void deleteCartNbpProduct(final Long cartNbpProductId) throws CartNbpProductNotFoundException {
        if (cartNbpProductRepository.findById(cartNbpProductId).isPresent()) {
            CartNbpProduct cartNbpProduct = cartNbpProductRepository.findById(cartNbpProductId).get();
            cartNbpProductRepository.deleteById(cartNbpProduct.getId());
        } else {
            throw new CartNbpProductNotFoundException();
        }
    }
}

