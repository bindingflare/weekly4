package com.flintintoe.weekly4.customer;

import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "customer", schema = "restaurant")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", nullable = false)
    private Integer id;

    @Column(name = "customer_name", nullable = false, length = 32)
    private String customerName;

    @Column(name = "phone_number", length = 16)
    private String phoneNumber;

    @Column(name = "address", length = 128)
    private String address;

    public void update(String customerName, String phoneNumber,String address) {
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
}