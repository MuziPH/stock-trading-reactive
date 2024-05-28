package io.glitchtech.stock_trading_app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.glitchtech.stock_trading_app.model.Stock;
import io.glitchtech.stock_trading_app.service.StocksService;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/stocks")
@AllArgsConstructor
public class StocksController {
    private StocksService stocksService;

    @GetMapping("/{id}")
    public Mono<Stock> getOneStock(@PathVariable String id) {
        return stocksService.getOneStock(id);
    }

    @GetMapping
    public Flux<Stock> getAllStocks() {
        return stocksService.getAllStocks();
    }

    @PostMapping
    public Mono<Stock> createStock(@RequestBody Stock stock) {
        return stocksService.createStock(stock);
    }
}
