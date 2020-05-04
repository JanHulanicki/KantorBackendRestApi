package com.app.kantor.domain.cartproduct;

import com.app.kantor.domain.cart.CartNbp;
import com.app.kantor.domain.cart.CartStock;
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
@Entity(name = "CARTS_STOCK_PRODUCTS")
public class CartStockProduct {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CART_PRODUCT_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CART_ID")
    private CartStock cartStock;

    @ManyToOne
    @JoinColumn(name = "STOCK_ID")
    private Stock stockProduct;


    @Column(name = "AMOUNT")
    private Double amount;
}