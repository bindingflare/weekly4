package com.flintintoe.weekly4.order;

import com.flintintoe.weekly4.customer.Customer;
import com.flintintoe.weekly4.store.Store;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "`order`", schema = "restaurant")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Column(name = "order_status", length = 16)
    private String orderStatus;

    @Column(name = "order_amt")
    private Float orderAmt;

    @Column(name = "order_time", nullable = false)
    private LocalDateTime orderTime;

    public void update(Customer customer, Store store, String orderStatus, Float orderAmt, LocalDateTime orderTime) {
        this.customer = customer;
        this.store = store;
        this.orderStatus = orderStatus;
        this.orderAmt = orderAmt;
        this.orderTime = orderTime;
    }

    public void updateTime() {
        this.orderTime = LocalDateTime.now();
    }
}