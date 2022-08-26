package com.nicolo.onlineshop.controller;
import com.nicolo.onlineshop.dto.CustomerRequest;
import com.nicolo.onlineshop.dto.OnlineShopResponse;
import com.nicolo.onlineshop.dto.LoginRequest;
import com.nicolo.onlineshop.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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

    @PostMapping(value = "/login" )//URL
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) throws Exception {
        OnlineShopResponse onlineShopResponse = new OnlineShopResponse();
        customerService.logIn(loginRequest);
        onlineShopResponse.setMessage("Login successful");
        return new ResponseEntity<>(onlineShopResponse.getMessage(),HttpStatus.OK);
    }
    
}
