package com.vshamota.demo.DTO;

import com.vshamota.demo.domain.Category;
import com.vshamota.demo.domain.Producer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewDeviceFormDTO {
    private String deviceDesc;
    private Integer QTY;
    private Float price;
    private Category category;
    private Producer producer;
}
