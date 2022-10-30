package com.nicolo.onlineshop.repository;
import com.nicolo.onlineshop.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    @Override
    Optional<Customer> findById(Long aLong);//find by id will give only one response therefore is not possible to have a list of customers in the method


}
