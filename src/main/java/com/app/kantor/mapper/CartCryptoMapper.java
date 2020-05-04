package com.app.kantor.mapper;

import com.app.kantor.domain.cart.CartCrypto;
import com.app.kantor.domain.cart.CartNbp;
import com.app.kantor.domain.cart.CartCryptoDto;
import com.app.kantor.exception.UserNotFoundException;
import com.app.kantor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartCryptoMapper {
    @Autowired
    UserRepository userRepository;

    public CartCryptoDto mapToCartCryptoDto(final CartCrypto cartCrypto) {
        return new CartCryptoDto(cartCrypto.getCartId(), cartCrypto.getCreated(), cartCrypto.getUser().getId());
    }

    public CartNbp mapToCartCrypto(final CartCryptoDto cartNbpDto) throws UserNotFoundException {
        CartNbp cartNbp = new CartNbp();
        cartNbp.setCartId(cartNbpDto.getCartId());
        cartNbp.setUser(userRepository.findById(cartNbpDto.getUserId()).orElseThrow(UserNotFoundException::new));
        cartNbp.setCreated(cartNbpDto.getCreated());
        return cartNbp;
    }
}
