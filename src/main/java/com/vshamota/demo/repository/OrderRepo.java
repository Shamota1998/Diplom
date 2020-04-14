package com.vshamota.demo.repository;

import com.vshamota.demo.domain.Orders;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepo extends CrudRepository<Orders, Integer> {
}
