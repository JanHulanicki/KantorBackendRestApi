package com.app.kantor.domain.cart;

import com.app.kantor.domain.cartproduct.CartCryptoProduct;
import com.app.kantor.domain.cartproduct.CartNbpProduct;
import com.app.kantor.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "CARTS_NBP")
public class CartNbp {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CART_ID")
    private Long cartId;
    @Column(name = "CREATED")
    private String created;

    public CartNbp(String created) {
        this.created = created;
    }

    @JoinColumn(name = "USER_ID", unique = true)
    @OneToOne(cascade = CascadeType.ALL)
    private User user;

   @OneToMany(
           targetEntity = CartNbpProduct.class,
           mappedBy = "cartNbp",
           orphanRemoval = true,
           cascade = CascadeType.PERSIST,
           fetch = FetchType.LAZY//EAGER
   )
    private List<CartNbpProduct> cartNbpProducts = new ArrayList<>();

}

