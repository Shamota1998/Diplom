package com.vshamota.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cityID;
    private String cityDesc;
    @ManyToOne
    @JoinColumn(name = "countryID")
    Country country;
    @OneToMany(mappedBy = "city")
    List<Address> addressList;
}
