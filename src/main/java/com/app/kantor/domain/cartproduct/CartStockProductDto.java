package com.app.kantor.domain.cartproduct;

import com.app.kantor.domain.cart.CartStockDto;
import com.app.kantor.domain.stock.StockDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CartStockProductDto {
    private Long id;
    private CartStockDto cartStockDto;
    private StockDto stockDto;
    private Double amount;
}
