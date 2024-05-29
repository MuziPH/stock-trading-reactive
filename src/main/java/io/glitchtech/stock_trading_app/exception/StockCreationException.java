package io.glitchtech.stock_trading_app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class StockCreationException extends RuntimeException {

    public StockCreationException(String message) {
        super(message);
    }

    // Utility method to build problem details
    public ProblemDetail asProblemDetail() {
        // Build the ProblemDetail using the returned Http status and the
        // RuntimeException message
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, getMessage());
        // Set the ProblemDetail Title message
        problemDetail.setTitle("Unable to Create Stock");
        return problemDetail;
    }
}
