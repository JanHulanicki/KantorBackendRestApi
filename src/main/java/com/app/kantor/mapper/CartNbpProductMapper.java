package com.app.kantor.mapper;

import com.app.kantor.domain.cart.CartNbpDto;
import com.app.kantor.domain.cartproduct.CartNbpProduct;
import com.app.kantor.domain.cartproduct.CartNbpProductDto;
import com.app.kantor.domain.nbp.NbpCurrencyDto;
import com.app.kantor.exception.CartNbpNotFoundException;
import com.app.kantor.repository.CartNbpRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartNbpProductMapper {
    private final static Logger LOGGER = LoggerFactory.getLogger(CartNbpProductMapper.class);
    @Autowired
    CartNbpMapper cartNbpMapper;
    @Autowired
    NbpCurrencyMapper nbpCurrencyMapper;
    @Autowired
    CartNbpRepository cartRepository;

    public CartNbpProductDto mapToCartNbpProductDto(final CartNbpProduct cartNbpProduct) {
        CartNbpDto cartNbpDto = cartNbpMapper.mapToCartNbpDto(cartNbpProduct.getCartNbp());
        NbpCurrencyDto nbpCurrencyDto = nbpCurrencyMapper.mapToNbpCurrencyDto(cartNbpProduct.getNbpProduct());
        return new CartNbpProductDto(cartNbpProduct.getId(), cartNbpDto, nbpCurrencyDto, cartNbpProduct.getAmount());
    }

    public CartNbpProduct mapToCartNbpProduct(final CartNbpProductDto cartProductDto) throws CartNbpNotFoundException {
        CartNbpProduct cartNbpProduct = new CartNbpProduct();
        cartNbpProduct.setId(cartProductDto.getId());
        cartNbpProduct.setCartNbp(cartRepository.findById(cartProductDto.getCartNbpDto().getCartId()).orElseThrow(CartNbpNotFoundException::new));
        cartNbpProduct.setAmount(cartProductDto.getAmount());
        return cartNbpProduct;
    }
}
