package com.vshamota.demo.service;

import com.vshamota.demo.repository.DeviceRepo;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
public class ProductsService {
    @Autowired
    private DeviceRepo deviceRepo;

    public static void getResultList(){

    }

}
