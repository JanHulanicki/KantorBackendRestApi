package com.app.kantor.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NbpCurrencyRatesDto {
    @SerializedName("no")
    @Expose
    public String no;
    @SerializedName("effectiveDate")
    @Expose
    public String effectiveDate;
    @SerializedName("mid")
    @Expose
    public Double mid;

    @Override
    public String toString() {
        return "NbpCurrencyRatesDto{" +
                "no='" + no + '\'' +
                ", effectiveDate='" + effectiveDate + '\'' +
                ", mid=" + mid +
                '}';
    }
}
