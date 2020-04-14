package com.vshamota.demo.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cat_id;
    private String cat_desc;
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Device> devices;

    public Integer getCat_id() {
        return this.cat_id;
    }

    public String getCat_desc() {
        return this.cat_desc;
    }

    public List<Device> getDevices() {
        return this.devices;
    }
}
