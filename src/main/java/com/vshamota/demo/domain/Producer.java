package com.vshamota.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Producer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer prod_id;
    private String firmDesc;
    @OneToMany(mappedBy = "producer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Device> devices;
}
