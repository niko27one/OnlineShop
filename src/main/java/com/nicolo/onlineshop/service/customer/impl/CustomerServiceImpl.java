package com.nicolo.onlineshop.service.customer.impl;
import com.nicolo.onlineshop.dto.AddressRequest;
import com.nicolo.onlineshop.dto.AddressResponse;
import com.nicolo.onlineshop.dto.ChangePasswordRequest;
import com.nicolo.onlineshop.dto.CustomerRequest;
import com.nicolo.onlineshop.dto.LoginRequest;
import com.nicolo.onlineshop.entity.Address;
import com.nicolo.onlineshop.entity.Customer;
import com.nicolo.onlineshop.entity.Login;
import com.nicolo.onlineshop.exceptions.AddressNotFoundException;
import com.nicolo.onlineshop.exceptions.CustomerAlreadyExistException;
import com.nicolo.onlineshop.exceptions.CustomerNotFoundException;
import com.nicolo.onlineshop.repository.AddressRepository;
import com.nicolo.onlineshop.repository.CustomerRepository;
import com.nicolo.onlineshop.repository.LogInRepository;
import com.nicolo.onlineshop.service.customer.CustomerService;
import com.nicolo.onlineshop.validation.impl.LoginValidation;
import com.nicolo.onlineshop.validation.impl.RegisterValidation;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;
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
  @Autowired
  private AddressRepository addressRepository;

  @Override
  public void register(CustomerRequest customerRequest) throws Exception {
    if (registerValidation.checkIfExist(customerRequest.getEmail())) {
      throw new CustomerAlreadyExistException("Customer already exists");//:)
    }
    Customer customer = Customer.builder().firstName(customerRequest.getFirstName())
        .lastName(customerRequest.getLastName()).dob(customerRequest.getDob())
        .mobileNo(customerRequest.getMobileNumber())
        .addresses(Arrays.asList(customerRequest.getAddresses()))
        .build();//arrays.aslist converte in una lista
    Address address = customer.getAddresses().get(0);
    address.setCustomer(customer);
    Login login = new Login();
    login.setCustomer(customer);
    login.setPassword(
        Base64Coder.encodeString(customerRequest.getPassword()));// per passare la password criptata
    login.setEmail(customerRequest.getEmail());
    customer.setLogin(login);
    customerRepository.save(customer);

  }

  @Override
  public void logIn(LoginRequest loginRequest) throws CustomerNotFoundException {
    loginRequest.setPassword(Base64Coder.encodeString(loginRequest.getPassword()));
    if (!loginValidation.checkIfExist(loginRequest)) {
      throw new CustomerNotFoundException("invalid username or password");
    }//this is it for login
    //logins.ifPresent(loginList -> System.out.println(loginList.get(0).getCustomer())); //fastest way
  }


  @Override
  public void changePassword(ChangePasswordRequest changePasswordRequest)
      throws CustomerNotFoundException {
    Optional<List<Login>> loginList = logInRepository.findByEmailAndPassword(
        changePasswordRequest.getEmail(),
        Base64Coder.encodeString(changePasswordRequest.getOldPassword()));
    if (loginList.isPresent() && loginList.get().size() > 0) {
      Login loginDetails = loginList.get().get(0);
      loginDetails.setPassword(Base64Coder.encodeString(changePasswordRequest.getNewPassword()));
      logInRepository.save(loginDetails);
    } else {
      throw new CustomerNotFoundException("invalid username or password");
    }
    //throw new CustomerNotFoundException("invalid username or password");
  }//this is it for login

  @Override
  public void changeCustomerMobileNo(Long customerId, CustomerRequest customerRequest)
      throws CustomerNotFoundException {
    Optional<Customer> customer = customerRepository.findById(customerId);
    if (customer.isPresent()) {
      customer.get().setMobileNo(customerRequest.getMobileNumber());
      customerRepository.save(customer.get());
    } else {
      throw new CustomerNotFoundException("customer not found");
    }// always use .get when we have Optional
  }

  @Override
  public List<AddressResponse> getAddresses(Long customerId) throws CustomerNotFoundException {
    Optional<Customer> customer = customerRepository.findById(customerId);
    if (customer.isPresent()) {
      List<AddressResponse> addressResponses = new ArrayList<>();
      for (Address address:customer.get().getAddresses()
      ) {
        AddressResponse addressResponse = new AddressResponse().builder().Id(address.getId()).
            city(address.getCity()).country(address.getCountry()).
            houseNo(address.getHouseNo()).postCode(address.getPostCode()).street(address.getStreet()).build();
        addressResponses.add(addressResponse);
      }
      return addressResponses;
    } else {
      throw new CustomerNotFoundException("Customer not found");
    }

  }

  @Override
  public void changeAddress(Long customerId, Long addressId, Address newAddress)
      throws AddressNotFoundException, CustomerNotFoundException {
    Optional<Customer> customer = customerRepository.findById(customerId);
    if (customer.isPresent()) {
      Optional<Address> address = addressRepository.findById(addressId);
      if (address.isPresent() && address.get().getCustomer() != null && address.get().getCustomer()
          .getId().equals(customerId)) {
        address.get().setCity(newAddress.getCity());
        address.get().setCountry(newAddress.getCountry());
        address.get().setHouseNo(newAddress.getHouseNo());
        address.get().setPostCode(newAddress.getPostCode());
        address.get().setIsDefault(newAddress.getIsDefault());
        addressRepository.save(address.get());
      } else {
        throw new AddressNotFoundException("invalid address");
      }
    } else {
      throw new CustomerNotFoundException("invalid customer");
    }
  }

  public AddressResponse getAddress(Long customerId, Long addressId)
      throws CustomerNotFoundException, AddressNotFoundException {
    Optional<Customer> customer = customerRepository.findById(customerId);
    if (customer.isPresent()) {
      Optional<Address> address = addressRepository.findById(addressId);
      if (address.isPresent() && address.get().getCustomer() != null && address.get().getCustomer()
          .getId().equals(customerId)) {
        AddressResponse addressResponse = new AddressResponse().builder().Id(addressId).
            city(address.get().getCity()).country(address.get().getCountry()).
            houseNo(address.get().getHouseNo()).postCode(address.get().getPostCode()).street(address.get().getStreet()).build();
        return addressResponse;
      } else {
        throw new AddressNotFoundException("invalid address");
      }
    } else {
      throw new CustomerNotFoundException("Customer not found");
    }
  }

  public ResponseEntity<String> addAddress(Long customerId, AddressRequest addressRequest) throws CustomerNotFoundException{
    Optional<Customer> customer = customerRepository.findById(customerId);
    if (customer.isPresent()) {
     Address address = new Address().builder().city(addressRequest.getCity()).houseNo(addressRequest.getHouseNo()).country(addressRequest.getCountry()).
         postCode(addressRequest.getPostCode()).street(addressRequest.getStreet()).customer(customer.get()).isDefault(addressRequest.getIsDefault()).build();
      addressRepository.save(address);
    }else {
      throw new CustomerNotFoundException("Customer not found");
    }
    return null;
  }

  public void deleteAddress(Long customerId, Long addressId) throws CustomerNotFoundException,AddressNotFoundException{
    Optional<Customer> customer = customerRepository.findById(customerId);
    if (customer.isPresent()) {
      Optional<Address> address = addressRepository.findById(addressId);
      if (address.isPresent() && address.get().getCustomer() != null && address.get().getCustomer()
          .getId().equals(customerId)){
      addressRepository.deleteById(addressId);
      }else {
        throw new AddressNotFoundException("invalid address");
      }
    }else {
      throw new CustomerNotFoundException("Customer not found");
    }
  }





}