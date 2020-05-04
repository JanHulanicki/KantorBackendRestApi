package com.app.kantor.domain.cartproduct;

import com.app.kantor.domain.cart.CartCrypto;
import com.app.kantor.domain.cart.CartNbp;
import com.app.kantor.domain.crypto.CryptoCurrency;
import com.app.kantor.domain.nbp.NbpCurrency;
import com.app.kantor.domain.stock.Stock;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "CARTS_CRYPTO_PRODUCTS")
public class CartCryptoProduct {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CART_PRODUCT_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CART_ID")
    private CartCrypto cartCrypto;

    @ManyToOne
    @JoinColumn(name = "CRYPTO_ID")
    private CryptoCurrency cryptoProduct;

    @Column(name = "AMOUNT")
    private Double amount;
}