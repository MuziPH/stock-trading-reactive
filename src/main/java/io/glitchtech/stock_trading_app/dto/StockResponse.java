package io.glitchtech.stock_trading_app.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.glitchtech.stock_trading_app.model.Stock;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockResponse {

    private String id;

    @JsonProperty("stockName")
    private String name;

    private BigDecimal price;

    private String currency;

    // Convert the internal model to a web response
    public static StockResponse fromModel(Stock stock) {
        return StockResponse.builder()
                .id(stock.getId())
                .name(stock.getName())
                .price(stock.getPrice())
                .currency(stock.getCurrency())
                .build();
    }
}
