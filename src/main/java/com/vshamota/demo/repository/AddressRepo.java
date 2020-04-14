package com.vshamota.demo.repository;

import com.vshamota.demo.domain.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepo extends CrudRepository<Address, Integer> {
}
