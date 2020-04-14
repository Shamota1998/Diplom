package com.vshamota.demo.repository;

import com.vshamota.demo.domain.Cart;
import com.vshamota.demo.domain.User;
import org.springframework.data.repository.CrudRepository;


public interface CartRepo extends CrudRepository<Cart, Integer> {
Cart findCartByUser(User user);

}
