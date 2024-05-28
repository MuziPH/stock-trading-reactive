package io.glitchtech.stock_trading_app.service;

import org.springframework.stereotype.Service;

import io.glitchtech.stock_trading_app.model.Stock;
import io.glitchtech.stock_trading_app.repository.StocksRepository;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class StocksService {
    private StocksRepository stocksRepository;

    // Return one stock
    public Mono<Stock> getOneStock(String id) {
        return stocksRepository.findById(id);
    }

    // Return a list of stocks
    public Flux<Stock> getAllStocks() {
        return stocksRepository.findAll();
    }

    public Mono<Stock> createStock(Stock stock) {
        return stocksRepository.save(stock);
    }
}
