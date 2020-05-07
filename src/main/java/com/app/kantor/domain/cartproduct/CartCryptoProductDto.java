package com.app.kantor.domain.cartproduct;

import com.app.kantor.domain.cart.CartCryptoDto;
import com.app.kantor.domain.crypto.CryptoCurrencyDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CartCryptoProductDto {
    private Long id;
    private CartCryptoDto cartCryptoDto;
    private CryptoCurrencyDto productCryptoDto;
    private Double amount;

    @Override
    public String toString() {
        return "CartCryptoProductDto{" +
                "id=" + id +
                ", cartCryptoDto=" + cartCryptoDto +
                ", productCryptoDto=" + productCryptoDto +
                ", amount=" + amount +
                '}';
    }
}
