package com.vshamota.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer addrID;
    private String addressDesc;
    @ManyToOne
    @JoinColumn(name = "cityID")
    private City city;

    @OneToOne(mappedBy = "address")
    UserDetails userDetails;
}
