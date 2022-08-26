package com.nicolo.onlineshop.entity;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer")
@Getter
@Setter
@Builder
public class Customer {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String firstName;
    private String lastName;
    private LocalDateTime dob;
    private String mobileNo;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "customer" )//Persist say to the hibernate object what to do with the dependency
    private List<Address> addresses;

    @OneToOne(cascade = CascadeType.ALL , mappedBy = "customer" )
    private Login login;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "customer" )//Persist say to the hibernate object what to do with the dependency
    private List<Order> orders;

}
