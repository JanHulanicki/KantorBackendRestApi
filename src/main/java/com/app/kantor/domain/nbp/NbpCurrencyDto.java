package com.app.kantor.domain.nbp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class NbpCurrencyDto {
    private Long _id;
  //  @SerializedName("table")
  //  @Expose
  //  public String table;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("rates")
    @Expose
    //public List<NbpCurrencyRatesDto> rates = null;
    private     NbpCurrencyRatesDto[] rates;

  @Override
  public String toString() {
    return "NbpCurrencyDto{" +
            "currency='" + currency + '\'' +
            ", code='" + code + '\'' +
            ", rates=" + Arrays.toString(rates) +
            '}';
  }
}

