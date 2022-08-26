package com.nicolo.onlineshop.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/*@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({LoginNotFoundException.class, CustomerNotFoundException.class, ProductNotFoundException.class, OrderNotFoundException.class})
    public final ResponseEntity<String> handleException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}*/


//this allows to handle generic exception too instead of only not-found
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<OnlineShopErrorResponse> handleException(CustomerAlreadyExistException exc) {

        // create CustomerErrorResponse

        OnlineShopErrorResponse error = new OnlineShopErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                exc.getMessage(),
                System.currentTimeMillis());

        // return ResponseEntity

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler
    public ResponseEntity<OnlineShopErrorResponse> handleException(CustomerNotFoundException exc) {

        // create CustomerErrorResponse

        OnlineShopErrorResponse error = new OnlineShopErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                exc.getMessage(),
                System.currentTimeMillis());

        // return ResponseEntity

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<OnlineShopErrorResponse> handleException(LoginNotFoundException exc) {

        // create CustomerErrorResponse

        OnlineShopErrorResponse error = new OnlineShopErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                exc.getMessage(),
                System.currentTimeMillis());

        // return ResponseEntity

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<OnlineShopErrorResponse> handleException(ProductNotFoundException exc) {

        // create CustomerErrorResponse

        OnlineShopErrorResponse error = new OnlineShopErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                exc.getMessage(),
                System.currentTimeMillis());

        // return ResponseEntity

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<OnlineShopErrorResponse> handleException(OrderNotFoundException exc) {

        // create CustomerErrorResponse

        OnlineShopErrorResponse error = new OnlineShopErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                exc.getMessage(),
                System.currentTimeMillis());

        // return ResponseEntity

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // Add another exception handler ... to catch any exception (catch all)

    @ExceptionHandler
    public ResponseEntity<OnlineShopErrorResponse> handleException(Exception exc) {

        // create CustomerErrorResponse

        OnlineShopErrorResponse error = new OnlineShopErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                exc.getMessage(),
                System.currentTimeMillis());

        // return ResponseEntity

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }




}
