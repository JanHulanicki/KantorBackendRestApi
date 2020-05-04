package com.app.kantor.domain.crypto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CRYPTO_CURRENCIES")
public class CryptoCurrency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

}
