package com.app.kantor.mapper;

import com.app.kantor.domain.cart.CartNbp;
import com.app.kantor.domain.cart.CartStock;
import com.app.kantor.domain.cart.CartStockDto;
import com.app.kantor.exception.UserNotFoundException;
import com.app.kantor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartStockMapper {
    @Autowired
    UserRepository userRepository;

    public CartStockDto mapToCartStockDto(final CartStock cartStock) {
        return new CartStockDto(cartStock.getCartId(), cartStock.getCreated(), cartStock.getUser().getId());
    }

    public CartNbp mapToCartStock(final CartStockDto cartNbpDto) throws UserNotFoundException {
        CartNbp cartNbp = new CartNbp();
        cartNbp.setCartId(cartNbpDto.getCartId());
        cartNbp.setUser(userRepository.findById(cartNbpDto.getUserId()).orElseThrow(UserNotFoundException::new));
        cartNbp.setCreated(cartNbpDto.getCreated());
        return cartNbp;
    }
}
