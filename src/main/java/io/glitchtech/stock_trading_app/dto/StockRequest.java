package io.glitchtech.stock_trading_app.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.glitchtech.stock_trading_app.model.Stock;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockRequest {

    @JsonProperty("stockName")
    private String name;
    private BigDecimal price;
    private String currency;

    // Convert th eincoming request data access object to the internal model
    public Stock toModel() {
        return Stock.builder()
                .name(this.name)
                .price(this.price)
                .currency(this.currency)
                .build();
    }
}
