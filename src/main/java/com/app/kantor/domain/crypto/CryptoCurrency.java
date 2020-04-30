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
    private Long _id;

    @Column(name = "currency")
    private String currency;

    @Column(name = "code")
    private String code;

    @Column(name = "date")
    private String date;

    @Column(name = "mid")
    private BigDecimal mid;

}
