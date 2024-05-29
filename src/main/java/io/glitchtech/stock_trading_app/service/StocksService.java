package io.glitchtech.stock_trading_app.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import io.glitchtech.stock_trading_app.dto.StockRequest;
import io.glitchtech.stock_trading_app.dto.StockResponse;
import io.glitchtech.stock_trading_app.exception.StockCreationException;
import io.glitchtech.stock_trading_app.exception.StockNotFoundException;
import io.glitchtech.stock_trading_app.repository.StocksRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@AllArgsConstructor
public class StocksService {
    private StocksRepository stocksRepository;

    // Return one stock
    public Mono<StockResponse> getOneStock(String id) {
        // Convert the internal model from the DB to the required http response
        return stocksRepository.findById(id)
                .map(stock -> StockResponse.fromModel(stock))// Returns empty Mono if not found
                .switchIfEmpty( // if Mono is empty return error
                        Mono.error(
                                new StockNotFoundException("Stock not found with id: " + id)// Initilaize with Throwable
                                                                                            // to handle the exception
                        ))
                .doFirst(() -> log.info("Retrieving stock with id:{} ", id))
                .doOnNext(stock -> log.info("Stock found with {}", stock))
                .doOnError(exception -> log.error("Something went wrong with signal type: {}", exception))
                .doOnTerminate(() -> log.info("Finalized retriving stock"))
                .doFinally(signalType -> log.info("Finalised retrieving stock with signal type: {}", signalType))

        ;
    }

    // Return a list of stocks
    public Flux<StockResponse> getAllStocks(BigDecimal priceGreaterThan) {
        return stocksRepository.findAll()
                .filter(stock -> stock.getPrice().compareTo(priceGreaterThan) > 0)
                .map(StockResponse::fromModel)
                .doFirst(() -> log.info("Retrieving all stocks"))
                .doOnNext(stockResponse -> log.info("Stock found: {}", stockResponse))
                .doOnError(exception -> log.warn("Something went wrong while retrieving stocks", exception))
                .doOnTerminate(() -> log.info("Finalized retrieving stocks"))
                .doFinally(signalType -> log.info("Finalized retrieving stocks with signal type: {}", signalType));
    }

    // Return a fallback value when you encouter an error
    public Mono<StockResponse> createStock(StockRequest stockRequest) {
        return Mono.just(stockRequest)
                // Convert incoming request to the stock entity/model
                .map(StockRequest::toModel)
                // flatMap unwraps the Mono<Stock> returned by stocksRepository.save(stock) to
                // Stock
                .flatMap(stock -> stocksRepository.save(stock))
                // Transfomr Stock model/entity to StockResponse for http resp
                .map(StockResponse::fromModel)
                // In case of error from above return below fallback StockResponse object
                // .onErrorReturn(StockResponse.builder().build());
                // Convert thrown exception to a custom exception with onErrorMap
                .onErrorMap(exception -> new StockCreationException(exception.getMessage()));
    }
}
