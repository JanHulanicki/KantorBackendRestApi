package com.app.kantor.domain.crypto;

import com.app.kantor.domain.cartproduct.CartCryptoProduct;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@PersistenceContext
@Table(name = "CRYPTO_CURRENCIES")
public class CryptoCurrency {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CRYPTO_ID")
    private Long _id;

    @Column(name = "CURRENCY")
    private String currency;

    @Column(name = "CODE")
    private String code;

    @Column(name = "DATE")
    private String date;

    @Column(name = "MID")
    private BigDecimal mid;
  //  @OneToMany(
 //           targetEntity = CartCryptoProduct.class,
 //           mappedBy = "cartCrypto",
 //           orphanRemoval = true,
 //           cascade = CascadeType.PERSIST,
 //           fetch = FetchType.LAZY//EAGER
 //   )
 //   private List<CartCryptoProduct> cartCryptoProducts = new ArrayList<>();


    public CryptoCurrency(String currency, String code, String date, BigDecimal mid) {
        this.currency = currency;
        this.code = code;
        this.date = date;
        this.mid = mid;
    }

    @Override
    public String toString() {
        return "CryptoCurrency{" +
                "_id=" + _id +
                ", currency='" + currency + '\'' +
                ", code='" + code + '\'' +
                ", date='" + date + '\'' +
                ", mid=" + mid +
                '}';
    }
}
