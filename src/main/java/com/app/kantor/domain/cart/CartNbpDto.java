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
public class CartNbpDto {
    private Long cartId;
    private Date created;
    private Long userId;

    public CartNbpDto(Date created) {
        this.created = created;
    }
}
