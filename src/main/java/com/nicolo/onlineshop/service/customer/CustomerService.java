package com.nicolo.onlineshop.service.customer;


import com.nicolo.onlineshop.dto.*;
import com.nicolo.onlineshop.entity.Address;
import com.nicolo.onlineshop.exceptions.AddressNotFoundException;
import com.nicolo.onlineshop.exceptions.CustomerNotFoundException;

import java.util.List;
import org.springframework.http.ResponseEntity;

public interface CustomerService {
    void register(CustomerRequest customerRequest) throws Exception;
    void logIn(LoginRequest loginRequest) throws Exception;

    void changePassword(ChangePasswordRequest changePasswordRequest)throws CustomerNotFoundException;

    void changeCustomerMobileNo(Long id, CustomerRequest customerRequest) throws CustomerNotFoundException;

    List<AddressResponse> getAddresses(Long customerId)throws CustomerNotFoundException;

    void changeAddress(Long customerId, Long addressId, Address newAddress)  throws AddressNotFoundException,CustomerNotFoundException;

    AddressResponse getAddress(Long customerId, Long addressId) throws CustomerNotFoundException, AddressNotFoundException;

    ResponseEntity<String> addAddress(Long customerId, AddressRequest addressRequest) throws CustomerNotFoundException;

    void deleteAddress(Long customerId, Long addressId) throws CustomerNotFoundException,AddressNotFoundException;
}
