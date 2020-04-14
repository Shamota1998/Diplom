package com.vshamota.demo.repository;

import com.vshamota.demo.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepo extends CrudRepository<Category, Integer> {
}
