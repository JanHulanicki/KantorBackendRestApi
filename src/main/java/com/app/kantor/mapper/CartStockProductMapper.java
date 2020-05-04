package com.app.kantor.mapper;

import com.app.kantor.domain.cart.CartStockDto;
import com.app.kantor.domain.cartproduct.CartStockProduct;
import com.app.kantor.domain.cartproduct.CartStockProductDto;
import com.app.kantor.domain.stock.StockDto;
import com.app.kantor.exception.CartStockNotFoundException;
import com.app.kantor.repository.CartStockRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartStockProductMapper {
    private final static Logger LOGGER = LoggerFactory.getLogger(CartStockProductMapper.class);
    @Autowired
    CartStockMapper cartStockMapper;
    @Autowired
    StockMapper stockMapper;
    @Autowired
    CartStockRepository cartStockRepository;

    public CartStockProductDto mapToCartStockProductDto(final CartStockProduct cartStockProduct) {
        CartStockDto cartStockDto = cartStockMapper.mapToCartStockDto(cartStockProduct.getCartStock());
        StockDto stockDto = stockMapper.mapToStockDto(cartStockProduct.getStockProduct());
        return new CartStockProductDto(cartStockProduct.getId(), cartStockDto, stockDto, cartStockProduct.getAmount());
    }

    public CartStockProduct mapToCartStockProduct(final CartStockProductDto cartStockProductDto) throws CartStockNotFoundException {
        CartStockProduct cartStockProduct = new CartStockProduct();
        cartStockProduct.setId(cartStockProductDto.getId());
        cartStockProduct.setCartStock(cartStockRepository.findById(cartStockProductDto.getCartStockDto().getCartId()).orElseThrow(CartStockNotFoundException::new));
        cartStockProduct.setAmount(cartStockProductDto.getAmount());
        return cartStockProduct;
    }
}
