package com.app.kantor.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class NbpCurrencyDto {
    @SerializedName("table")
    @Expose
    public String table;
    @SerializedName("currency")
    @Expose
    public String currency;
    @SerializedName("code")
    @Expose
    public String code;
    @SerializedName("rates")
    @Expose
    public List<NbpCurrencyRatesDto> rates = null;

    @Override
    public String toString() {
        return "NbpCurrencyDto{" +
                "table='" + table + '\'' +
                ", currency='" + currency + '\'' +
                ", code='" + code + '\'' +
                ", rates=" + rates +
                '}';
    }
}
