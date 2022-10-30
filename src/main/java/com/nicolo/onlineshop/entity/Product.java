package com.nicolo.onlineshop.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
@Getter
@Setter
@Builder
public class Product {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String barcode;
    private String name;
    private Long price;
    private String description;
    private String image;
    //to make image a list and a separate class and review class,

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH, CascadeType.REFRESH} )
    @JoinColumn(name="orders_id")
    private Order orders;
}
