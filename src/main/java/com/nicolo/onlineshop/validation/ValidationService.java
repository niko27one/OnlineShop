package com.nicolo.onlineshop.validation;

public interface ValidationService<T>{
    boolean checkIfExist(T arg) throws Exception;

}
