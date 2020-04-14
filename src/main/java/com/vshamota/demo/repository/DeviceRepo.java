package com.vshamota.demo.repository;

import com.vshamota.demo.domain.Category;
import com.vshamota.demo.domain.Device;
import com.vshamota.demo.domain.Producer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Collection;
import java.util.List;

public interface DeviceRepo extends CrudRepository<Device, Integer>, PagingAndSortingRepository<Device, Integer> {

    List<Device> findAllByCategoryInAndPriceBetweenAndProducerEqualsOrderByPriceAsc(
            Collection<Category> categories, float min, float max, Producer producer);

    List<Device> findAllByCategoryInAndPriceBetweenAndProducerEqualsOrderByPriceDesc(
            Collection<Category> categories, float min, float max, Producer producer);

    Page<Device> findAllByCategoryInAndPriceBetweenOrderByPriceAsc(
            Collection<Category> categories, float min, float max, Pageable pageable);

    Page<Device> findAllByCategoryInAndPriceBetweenOrderByPriceDesc(
            Collection<Category> categories, float min, float max, Pageable pageable);

    @Override
    Page<Device> findAll(Pageable pageable);
}
