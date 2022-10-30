package com.nicolo.onlineshop.repository;
import com.nicolo.onlineshop.entity.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AddressRepository extends CrudRepository<Address,Long> {
    Optional<Address> findById(Long id);

    void deleteById(Long Id);
}
