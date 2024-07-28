package com.flintintoe.weekly4.store;

import com.flintintoe.weekly4.customer.Customer;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "store", schema = "restaurant")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id", nullable = false)
    private Integer id;

    @Column(name = "name", length = 64)
    private String name;

    @Column(name = "address", length = 128)
    private String address;

    @Column(name = "phone_number", length = 16)
    private String phoneNumber;

    public void update(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}