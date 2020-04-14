package com.vshamota.demo.repository;

import com.vshamota.demo.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Integer> {
    User findByLogin(String username);
}
