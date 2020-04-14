package com.vshamota.demo.DTO;

import com.vshamota.demo.domain.Device;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@NoArgsConstructor
@Data
public class FinalOrderDTO {
    private Device device;
    private Integer qty;
    private Float totalPrice;

}