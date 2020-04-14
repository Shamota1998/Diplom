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
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartId;
    @OneToOne
    @JoinColumn(name="user_id")
    private User user;


    @ManyToMany
    @JoinTable(
            name="cart_device",
            joinColumns = {@JoinColumn(name="cartId")},
            inverseJoinColumns = {@JoinColumn(name="dev_id")}
    )
    private Set<Device> devices;

    public void addDevice(Device device){
        devices.add(device);
    }

    public void deleteProduct(Device device){
        devices.remove(device);
    }
}
