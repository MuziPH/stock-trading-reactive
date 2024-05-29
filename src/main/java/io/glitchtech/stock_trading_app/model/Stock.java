package io.glitchtech.stock_trading_app.model;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class Stock {

    @Id
    private String id;

    private String name;

    @NonNull
    private BigDecimal price;

    private String currency;

}
