package com.nicolo.onlineshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class AddressRequest {
    private String street;
    private String houseNo;
    private String city;
    private String postCode;
    private String country;
    private Boolean isDefault;
}
