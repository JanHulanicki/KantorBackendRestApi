package com.app.kantor.domain.stock;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "NYSE_STOCK")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long _id;

    @Column(name = "stock")
    private String currency;

    @Column(name = "code")
    private String code;

    @Column(name = "date")
    private String date;

    @Column(name = "mid")
    private BigDecimal mid;
    @Column(name = "symbol")
    private String symbol;
    @Column(name = "open")
    private BigDecimal Open;
    @Column(name = "high")
    private BigDecimal high;
    @Column(name = "low")
    private BigDecimal low;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "volume")
    private BigDecimal volume;
    @Column(name = "latesTradingDay")
    private String latestTradingDay;
    @Column(name = "previousClose")
    private BigDecimal previousClose;
    @Column(name = "change")
    private BigDecimal change;
}
