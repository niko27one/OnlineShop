package com.nicolo.onlineshop.repository;
import com.nicolo.onlineshop.entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    @Override
    Optional<Order> findById(Long aLong);
}

