package com.app.kantor.domain.crypto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RealtimeCurrencyExchangeRatedDto {
    private Long _id;
    @SerializedName("1. From_Currency Code")
    @Expose
    public String code;
    @SerializedName("2. From_Currency Name")
    @Expose
    public String currency;
//    @SerializedName("3. To_Currency Code")
//    @Expose
//    public String _3ToCurrencyCode;
//    @SerializedName("4. To_Currency Name")
//    @Expose
//    public String _4ToCurrencyName;
    @SerializedName("5. Exchange Rate")
    @Expose
    public BigDecimal mid;
    @SerializedName("6. Last Refreshed")
    @Expose
    public String date;
//    @SerializedName("7. Time Zone")
//    @Expose
//    public String _7TimeZone;
//    @SerializedName("8. Bid Price")
//    @Expose
//    public String _8BidPrice;
//    @SerializedName("9. Ask Price")
//    @Expose
//    public String _9AskPrice;


    @Override
    public String toString() {
        return "RealtimeCurrencyExchangeRatedDto{" +
                "code='" + code + '\'' +
                ", currency='" + currency + '\'' +
                ", mid=" + mid +
                ", date='" + date + '\'' +
                '}';
    }
}
