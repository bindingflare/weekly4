package com.flintintoe.weekly4.order;

import com.flintintoe.weekly4.customer.Customer;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "`order`", schema = "restaurant")
public class Order {
    @Id
    @Column(name = "order_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "order_status", length = 16)
    private String orderStatus;

    @Column(name = "order_amt")
    private Float orderAmt;

    public void update(Customer customer, String orderStatus, Float orderAmt) {
        this.customer = customer;
        this.orderStatus = orderStatus;
        this.orderAmt = orderAmt;
    }
}