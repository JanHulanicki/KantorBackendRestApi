package com.app.kantor.domain.cartproduct;

import com.app.kantor.domain.cart.CartCrypto;
import com.app.kantor.domain.cart.CartCryptoDto;
import com.app.kantor.domain.cart.CartNbpDto;
import com.app.kantor.domain.crypto.CryptoCurrencyDto;
import com.app.kantor.domain.nbp.NbpCurrencyDto;
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
}
