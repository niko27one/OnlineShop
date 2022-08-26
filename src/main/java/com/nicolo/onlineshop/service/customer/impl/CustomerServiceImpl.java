package com.nicolo.onlineshop.service.customer.impl;
import com.nicolo.onlineshop.dto.CustomerRequest;
import com.nicolo.onlineshop.dto.LoginRequest;
import com.nicolo.onlineshop.entity.Customer;
import com.nicolo.onlineshop.entity.Login;
import com.nicolo.onlineshop.exceptions.CustomerAlreadyExistException;
import com.nicolo.onlineshop.exceptions.CustomerNotFoundException;
import com.nicolo.onlineshop.repository.CustomerRepository;
import com.nicolo.onlineshop.repository.LogInRepository;
import com.nicolo.onlineshop.service.customer.CustomerService;
import com.nicolo.onlineshop.validation.impl.LoginValidation;
import com.nicolo.onlineshop.validation.impl.RegisterValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private RegisterValidation registerValidation;
    @Autowired
    private LoginValidation loginValidation;
    @Autowired
    private LogInRepository logInRepository;

    @Override
    public void register(CustomerRequest customerRequest) throws Exception {
        if(registerValidation.checkIfExist(customerRequest.getEmail())){
            throw new CustomerAlreadyExistException("Customer already exists");//:)
        }
        Customer customer = Customer.builder().firstName(customerRequest.getFirstName()).lastName(customerRequest.getLastName()).dob(customerRequest.getDob()).mobileNo(customerRequest.getMobileNumber()).addresses(Arrays.asList(customerRequest.getAddresses())).build();//arrays.aslist converte in una lista
        Login login = new Login();
        login.setCustomer(customer);
        login.setPassword(Base64Coder.encodeString(customerRequest.getPassword()));// per passare la password criptata
        login.setEmail(customerRequest.getEmail());
        customer.setLogin(login);
        customerRepository.save(customer);
    }

    @Override
    public void logIn(LoginRequest loginRequest) throws CustomerNotFoundException {
        loginRequest.setPassword(Base64Coder.decodeString(loginRequest.getPassword()));
        if(!loginValidation.checkIfExist(loginRequest)){
            throw new CustomerNotFoundException("Customer not found");
        }//this is it for login
        //logins.ifPresent(loginList -> System.out.println(loginList.get(0).getCustomer())); //fastest way
    }


}
