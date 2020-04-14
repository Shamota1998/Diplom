package com.vshamota.demo.DTO;

import com.vshamota.demo.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FilterTmpDTO {
    private Float minPrice;
    private Float maxPrice;
    private List<Category> category;
    private String producer;

    private String sort;
}