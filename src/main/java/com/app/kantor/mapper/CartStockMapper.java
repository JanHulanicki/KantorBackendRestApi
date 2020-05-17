package com.app.kantor.mapper;

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

    public CartStock mapToCartStock(final CartStockDto cartStockDto) throws UserNotFoundException {
        CartStock cartStock = new CartStock();
        cartStock.setCartId(cartStockDto.getCartId());
        cartStock.setUser(userRepository.findById(cartStockDto.getUserId()).orElseThrow(UserNotFoundException::new));
        cartStock.setCreated(cartStockDto.getCreated());
        return cartStock;
    }
}
