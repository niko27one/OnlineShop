package com.nicolo.onlineshop.dto;
import com.nicolo.onlineshop.entity.Address;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CustomerRequest {

    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private LocalDateTime dob;
    private String mobileNumber;
    private Address addresses;

}
