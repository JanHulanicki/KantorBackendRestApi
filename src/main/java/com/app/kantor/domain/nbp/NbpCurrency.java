package com.app.kantor.domain.nbp;

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
@Table(name = "NBP_CURRENCIES")
public class NbpCurrency {
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

    public NbpCurrency(String currency, String code, String date, BigDecimal mid) {
        this.currency = currency;
        this.code = code;
        this.date = date;
        this.mid = mid;
    }
}
