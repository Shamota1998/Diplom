package com.vshamota.demo.DTO;

import com.vshamota.demo.domain.Category;
import com.vshamota.demo.domain.Producer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FilterDTO {
    private Float minPrice;
    private Float maxPrice;
    private List<Category> category;
    private Producer producer;
    private String sort;
}
