package com.vshamota.demo.DTO;

import com.vshamota.demo.domain.Device;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO {
    private List<Device> dev;
    private List<Integer> qty;
//    private Device dev;
////    private Integer qty;
//    private Float totalPrice;
private Float totalPrice;
}
