package io.glitchtech.stock_trading_app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class StockExceptionHandler {

    // To execute evrytime the stock is not found
    @ExceptionHandler(StockNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ProblemDetail handleStockNotFoundException(StockNotFoundException exception) {
        // Build the ProblemDetail wth the returned http status and exception message
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, exception.getMessage());
        // Set the return summarised title
        problemDetail.setTitle("Stock not found");
        return problemDetail;
    }

    @ExceptionHandler(StockCreationException.class) // method to handle exception when creating stock
    @ResponseStatus(HttpStatus.BAD_REQUEST) // Http status returned for the excption
    public ProblemDetail handleStockCreationException(StockCreationException exception) {
        // Cleaner approach using autility method to build the ProblemDetail
        return exception.asProblemDetail();
    }
}
