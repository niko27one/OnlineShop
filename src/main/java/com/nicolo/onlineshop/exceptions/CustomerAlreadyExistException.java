package com.nicolo.onlineshop.exceptions;

public class CustomerAlreadyExistException extends Exception {
    public CustomerAlreadyExistException(String message){
        super(message);
    }
}
