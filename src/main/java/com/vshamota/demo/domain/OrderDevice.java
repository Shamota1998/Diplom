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
@Table(name="order_device")
public class OrderDevice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="od_id")
    private Integer odID;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders order;
    @ManyToOne
    @JoinColumn(name = "dev_id")
    private Device device;
    private Integer qty;
}
