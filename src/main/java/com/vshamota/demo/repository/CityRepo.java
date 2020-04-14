package com.vshamota.demo.repository;

import com.vshamota.demo.domain.City;
import org.springframework.data.repository.CrudRepository;

public interface CityRepo extends CrudRepository<City, Integer> {
}
//    select u.first_name, u.last_name, a.address_desc, c.city_desc,
//    countr.country_desc from user_details u, address a, city c, country countr
//     where u.addrid = a.addrid and a.cityid = c.cityid and c.countryid = countr.country_id;