package com.nicolo.onlineshop.dto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class AddressResponse {
    private Long Id;
    private String street;
    private String houseNo;
    private String city;
    private String postCode;
    private String country;
    private Boolean isDefault;
}
