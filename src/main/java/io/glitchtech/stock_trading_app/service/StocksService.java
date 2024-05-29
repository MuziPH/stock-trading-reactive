package io.glitchtech.stock_trading_app.service;

import org.springframework.stereotype.Service;

import io.glitchtech.stock_trading_app.dto.StockRequest;
import io.glitchtech.stock_trading_app.dto.StockResponse;
import io.glitchtech.stock_trading_app.repository.StocksRepository;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class StocksService {
    private StocksRepository stocksRepository;

    // Return one stock
    public Mono<StockResponse> getOneStock(String id) {
        // Convert the internal model from the DB to the required http response
        return stocksRepository.findById(id)
                .map(stock -> StockResponse.fromModel(stock));
    }

    // Return a list of stocks
    public Flux<StockResponse> getAllStocks() {
        return stocksRepository.findAll()
                .map(StockResponse::fromModel);
    }

    public Mono<StockResponse> createStock(StockRequest stockRequest) {
        return stocksRepository.save(stockRequest.toModel()) // Convert incoming request to the entity
                .map(StockResponse::fromModel); // Convert back the model/entity to the response entity
    }
}
