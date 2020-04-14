package com.vshamota.demo.repository;

import com.vshamota.demo.domain.UserDetails;
import org.springframework.data.repository.CrudRepository;

public interface UserDetailsRepo extends CrudRepository<UserDetails, Integer> {
}
