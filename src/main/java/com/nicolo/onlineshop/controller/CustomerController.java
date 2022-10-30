package com.nicolo.onlineshop.controller;
import com.nicolo.onlineshop.dto.*;
import com.nicolo.onlineshop.entity.Address;
import com.nicolo.onlineshop.exceptions.CustomerNotFoundException;
import com.nicolo.onlineshop.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/v1/customers")// endpoint sempre small case e plurale
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping("/")//URL
    public ResponseEntity<String> register(@RequestBody CustomerRequest customerRequest) throws Exception {
        OnlineShopResponse onlineShopResponse = new OnlineShopResponse();
        if (customerRequest.getFirstName()== null || customerRequest.getFirstName().isEmpty() || customerRequest.getDob() == null || customerRequest.getMobileNumber() == null || customerRequest.getMobileNumber().isEmpty() || customerRequest.getEmail() == null || customerRequest.getEmail().isEmpty() || customerRequest.getPassword() == null || customerRequest.getPassword().isEmpty() || customerRequest.getLastName() == null || customerRequest.getLastName().isEmpty() ){
            onlineShopResponse.setMessage("Some fields are missing");
            return new ResponseEntity<>(onlineShopResponse.getMessage(), HttpStatus.BAD_REQUEST);
        }

        if(!customerRequest.getFirstName().matches("[A-Za-z]*")){
            onlineShopResponse.setMessage("only plain characters accepted, please re-enter the name");
            return new ResponseEntity<>(onlineShopResponse.getMessage(), HttpStatus.BAD_REQUEST);
        }
        customerService.register(customerRequest);
        onlineShopResponse.setMessage("Customer created");
        return new ResponseEntity<>(onlineShopResponse.getMessage(), HttpStatus.CREATED);
    }

    //@GetMapping(value = "/customer/{customerId}")


    @PostMapping(value = "/login" )//URL
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) throws Exception {
        OnlineShopResponse onlineShopResponse = new OnlineShopResponse();
        customerService.logIn(loginRequest);
        onlineShopResponse.setMessage("Login successful");
        return new ResponseEntity<>(onlineShopResponse.getMessage(),HttpStatus.OK);
    }

    @PutMapping(value = "/login")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) throws Exception{
        OnlineShopResponse onlineShopResponse = new OnlineShopResponse();
        customerService.changePassword(changePasswordRequest);
        onlineShopResponse.setMessage("Password changed");
        return new ResponseEntity<>(onlineShopResponse.getMessage(),HttpStatus.OK);
    }

    @PutMapping(value = "/{customerId}/PhoneNo")
    public ResponseEntity<String> PhoneNo(@PathVariable(value = "customerId") final Long id,@RequestBody CustomerRequest customerRequest ) throws Exception{
        OnlineShopResponse onlineShopResponse = new OnlineShopResponse();
        customerService.changeCustomerMobileNo(id,customerRequest);
        onlineShopResponse.setMessage("phone number changed");
        return new ResponseEntity<>(onlineShopResponse.getMessage(),HttpStatus.OK);
    }

    @PutMapping(value = "/{customerId}/addresses/{addressId}")
    public ResponseEntity<String> changeAddress(@PathVariable(value = "customerId") final Long customerId,@PathVariable(value = "addressId") final Long addressId,@RequestBody Address address) throws Exception{
        OnlineShopResponse onlineShopResponse = new OnlineShopResponse();
        customerService.changeAddress(customerId,addressId,address);
        onlineShopResponse.setMessage(" address changes saved");
        return new ResponseEntity<>(onlineShopResponse.getMessage(),HttpStatus.OK);
    }

    @GetMapping(value = "/{customerId}/addresses")
    public List<AddressResponse> getAllAddresses(@PathVariable(value = "customerId") final Long customerId) throws Exception{
        return customerService.getAddresses(customerId);
    }

    //create an api to get one address by id.
    @GetMapping(value = "/{customerId}/addresses/{addressId}")
    public AddressResponse getAddress(@PathVariable(value = "customerId") final Long customerId, @PathVariable(value = "addressId") final Long addressId) throws Exception{
       return customerService.getAddress( customerId,addressId);
    }

    @PostMapping(value = "{customerId}/addresses")
    public ResponseEntity<String> createAddress(@PathVariable final Long customerId, @RequestBody AddressRequest addressRequest)
        throws CustomerNotFoundException {
        OnlineShopResponse onlineShopResponse = new OnlineShopResponse();
        customerService.addAddress(customerId,addressRequest);
        onlineShopResponse.setMessage("address created");
        return new  ResponseEntity<>(onlineShopResponse.getMessage(),HttpStatus.OK);
    }

    @DeleteMapping(value = "/{customerId}/addresses/{addressId}")
    public ResponseEntity<String> deleteAddress (@PathVariable(value = "customerId") final Long customerId, @PathVariable(value = "addressId") final Long addressId)throws Exception{
        OnlineShopResponse onlineShopResponse = new OnlineShopResponse();
        customerService.deleteAddress(customerId,addressId);
        onlineShopResponse.setMessage("address deleted");
        return new  ResponseEntity<>(onlineShopResponse.getMessage(),HttpStatus.OK);
    }
}

