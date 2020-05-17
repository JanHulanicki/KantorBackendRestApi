package com.app.kantor.domain.cartproduct;

import com.app.kantor.domain.cart.CartNbpDto;
import com.app.kantor.domain.nbp.NbpCurrencyDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartNbpProductDto {
    private Long id;
    private CartNbpDto cartNbpDto;
    private NbpCurrencyDto productNbpDto;
    private Double amount;
}
