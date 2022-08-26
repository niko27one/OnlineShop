package com.nicolo.onlineshop.exceptions;

public class CustomerNotFoundException extends  Exception{
    public CustomerNotFoundException(String message){
        super(message);
    }
}
