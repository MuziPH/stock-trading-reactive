package io.glitchtech.stock_trading_app.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import io.glitchtech.stock_trading_app.model.Stock;

@Repository
public interface StocksRepository extends ReactiveMongoRepository<Stock, String> {

}
