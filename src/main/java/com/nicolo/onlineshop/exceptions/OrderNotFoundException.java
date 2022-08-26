package com.nicolo.onlineshop.exceptions;

public class OrderNotFoundException extends  Exception{
    public OrderNotFoundException(String message){
        super(message);
    }
}
