package com.app.kantor.mapper;

import com.app.kantor.domain.cart.CartNbp;
import com.app.kantor.domain.cart.CartNbpDto;
import com.app.kantor.exception.UserNotFoundException;
import com.app.kantor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartNbpMapper {
    @Autowired
    UserRepository userRepository;

    public CartNbpDto mapToCartNbpDto(final CartNbp cartNbp) {
        return new CartNbpDto(cartNbp.getCartId(), cartNbp.getCreated(), cartNbp.getUser().getId());
    }

    public CartNbp mapToCartNbp(final CartNbpDto cartNbpDto) throws UserNotFoundException {
        CartNbp cartNbp = new CartNbp();
        cartNbp.setCartId(cartNbpDto.getCartId());
        cartNbp.setUser(userRepository.findById(cartNbpDto.getUserId()).orElseThrow(UserNotFoundException::new));
        cartNbp.setCreated(cartNbpDto.getCreated());
        return cartNbp;
    }
}
