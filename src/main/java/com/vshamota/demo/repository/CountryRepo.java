package com.vshamota.demo.repository;

import com.vshamota.demo.domain.Country;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepo extends CrudRepository<Country, Integer> {
}
