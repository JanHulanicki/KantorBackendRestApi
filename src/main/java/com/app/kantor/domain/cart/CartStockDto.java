package com.app.kantor.domain.cart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartStockDto {
    private Long cartId;
    private String created;
    private Long userId;

    public CartStockDto(String created) {
        this.created = created;
    }
}
