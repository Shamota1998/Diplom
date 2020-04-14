package com.vshamota.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer order_id;
//    @ManyToMany
//    @JoinTable(
//            name = "Order_Device",
//            joinColumns = {@JoinColumn(name = "order_id")},
//            inverseJoinColumns = {@JoinColumn(name = "dev_id")}
//    )
//    private List<Device> devices;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDate date;

//    private Integer QTY;

    @OneToMany(mappedBy = "order")
    private List<OrderDevice> orderDevices;

}
