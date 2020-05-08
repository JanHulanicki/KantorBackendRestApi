package com.app.kantor.domain.cartproduct;

import com.app.kantor.domain.cart.CartCrypto;
import com.app.kantor.domain.crypto.CryptoCurrency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "CARTS_CRYPTO_PRODUCTS")
@PersistenceContext
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
    private CryptoCurrency cryptoCurrency;

    @Column(name = "AMOUNT")
    private Double amount;

    @Override
    public String toString() {
        return "CartCryptoProduct{" +
                "id=" + id +
                ", cartCrypto=" + cartCrypto +
                ", cryptoCurrency=" + cryptoCurrency +
                ", amount=" + amount +
                '}';
    }
}
