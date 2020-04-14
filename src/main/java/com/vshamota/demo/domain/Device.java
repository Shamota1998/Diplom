package com.vshamota.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Device {
    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;
    @ManyToOne
    @JoinColumn(name = "producer_id")
    Producer producer;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer dev_id;
    private String device_desc;
    private Integer QTY;
    private Float price;
    @ManyToMany(mappedBy = "devices")
    private Set<Cart> carts;
    //    @ManyToMany(mappedBy = "devices")
//    private Set<Orders> orders;
    @OneToMany(mappedBy = "device")
    private List<OrderDevice> orderDevices;
}
