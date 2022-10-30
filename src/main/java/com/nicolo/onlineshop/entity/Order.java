package com.nicolo.onlineshop.entity;
import lombok.*;
import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")// order cannot be used because is an SQL syntax and therefore we must change it to orders
@Getter
@Setter
@Builder
public class Order {

    @Id
    @Column(name = "orders_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private Long price;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="customer_id")
    private Customer customer;

   @OneToMany(cascade = CascadeType.ALL , mappedBy = "orders" )
   private List<Product> products;


}
