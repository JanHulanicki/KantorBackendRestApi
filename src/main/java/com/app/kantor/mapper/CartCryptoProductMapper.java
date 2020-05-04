package com.app.kantor.mapper;

import com.app.kantor.domain.cart.CartCryptoDto;
import com.app.kantor.domain.cartproduct.CartCryptoProduct;
import com.app.kantor.domain.cartproduct.CartCryptoProductDto;
import com.app.kantor.domain.crypto.CryptoCurrencyDto;
import com.app.kantor.exception.CartCryptoNotFoundException;
import com.app.kantor.repository.CartCryptoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartCryptoProductMapper {
    private final static Logger LOGGER = LoggerFactory.getLogger(CartCryptoProductMapper.class);
    @Autowired
    CartCryptoMapper cartCryptoMapper;
    @Autowired
    CryptoCurrencyMapper cryptoCurrencyMapper;
    @Autowired
    CartCryptoRepository cartCryptoRepository;

    public CartCryptoProductDto mapToCartCryptoProductDto(final CartCryptoProduct cartCryptoProduct) {
        CartCryptoDto cartCryptoDto = cartCryptoMapper.mapToCartCryptoDto(cartCryptoProduct.getCartCrypto());
        CryptoCurrencyDto cryptoCurrencyDto = cryptoCurrencyMapper.mapToCryptoCurrencyDto(cartCryptoProduct.getCryptoCurrency());
        return new CartCryptoProductDto(cartCryptoProduct.getId(), cartCryptoDto, cryptoCurrencyDto, cartCryptoProduct.getAmount());
    }

    public CartCryptoProduct mapToCartCryptoProduct(final CartCryptoProductDto cartCryptoProductDto) throws CartCryptoNotFoundException {
        CartCryptoProduct cartCryptoProduct = new CartCryptoProduct();
        cartCryptoProduct.setId(cartCryptoProductDto.getId());
        cartCryptoProduct.setCartCrypto(cartCryptoRepository.findById(cartCryptoProductDto.getCartCryptoDto().getCartId()).orElseThrow(CartCryptoNotFoundException::new));
        cartCryptoProduct.setAmount(cartCryptoProductDto.getAmount());
        return cartCryptoProduct;
    }
}
