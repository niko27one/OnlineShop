package com.nicolo.onlineshop.entity;
import lombok.*;
import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "address")
@Getter
@Setter
@Builder
public class Address {

    @Id
    @Column(name = "address_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String street;
    private String houseNo;
    private String city;
    private String postCode;
    private String country;
    private Boolean isDefault;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
        CascadeType.DETACH, CascadeType.REFRESH})//if is join colum, no mapped by is needed
    @JoinColumn(name="customer_id")
    private Customer customer;
}
