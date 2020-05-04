package com.app.kantor.domain.cart;

import com.app.kantor.domain.cartproduct.CartCryptoProduct;
import com.app.kantor.domain.cartproduct.CartStockProduct;
import com.app.kantor.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "CARTS_STOCK")
public class CartStock {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CART_ID")
    private Long cartId;
    @Column(name = "CREATED")
    private Date created;

    public CartStock(Date created) {
        this.created = created;
    }

    @JoinColumn(name = "USER_ID", unique = true)
    @OneToOne(cascade = CascadeType.ALL)
    private User user;

   @OneToMany(
           targetEntity = CartStockProduct.class,
           mappedBy = "cartStock",
           orphanRemoval = true,
           cascade = CascadeType.PERSIST,
           fetch = FetchType.LAZY//EAGER
   )
    private List<CartStockProduct> cartStockProducts = new ArrayList<>();

}

