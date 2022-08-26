package com.nicolo.onlineshop.service.customer;


import com.nicolo.onlineshop.dto.Information;
import com.nicolo.onlineshop.dto.LoginRequest;
import com.nicolo.onlineshop.dto.CustomerRequest;

public interface CustomerService {
    void register(CustomerRequest customerRequest) throws Exception;
    void logIn(LoginRequest loginRequest) throws Exception;

}
