package com.app.kantor.domain.cartproduct;

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
@Entity(name = "CARTS_NBP_PRODUCTS")
public class CartNbpProduct {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CART_PRODUCT_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CART_ID")
    private CartNbp cartNbp;

    @ManyToOne
    @JoinColumn(name = "NBP_ID")
    private NbpCurrency nbpProduct;
    @Column(name = "AMOUNT")
    private Double amount;
}