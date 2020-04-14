package com.vshamota.demo.repository;

import com.vshamota.demo.domain.Producer;
import org.springframework.data.repository.CrudRepository;

public interface ProducerRepo extends CrudRepository<Producer, Integer> {
    Producer findByFirmDesc(String firmDesc);
}
