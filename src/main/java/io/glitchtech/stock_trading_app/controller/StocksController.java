package io.glitchtech.stock_trading_app.controller;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.glitchtech.stock_trading_app.dto.StockRequest;
import io.glitchtech.stock_trading_app.dto.StockResponse;
import io.glitchtech.stock_trading_app.model.Stock;
import io.glitchtech.stock_trading_app.service.StocksService;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@AllArgsConstructor
@RequestMapping("/stocks")
public class StocksController {
    private StocksService stocksService;

    @GetMapping("/{id}")
    public Mono<StockResponse> getOneStock(@PathVariable("id") String id) {
        return stocksService.getOneStock(id);
    }

    @GetMapping
    public Flux<StockResponse> getAllStocks(
            @RequestParam(required = false, defaultValue = "0", name = "priceGreaterThan") BigDecimal priceGreaterThan) {
        return stocksService.getAllStocks(priceGreaterThan);
    }

    @PostMapping
    public Mono<StockResponse> createStock(@RequestBody StockRequest stockRequest) {
        return stocksService.createStock(stockRequest);
    }
}
