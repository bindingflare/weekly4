package com.flintintoe.weekly4.order;

import com.flintintoe.weekly4.customer.CustomerDto;
import com.flintintoe.weekly4.store.StoreDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link Order}
 */
@Value
@AllArgsConstructor
@Builder
public class OrderDto implements Serializable {
    Integer id;
    CustomerDto customer;
    StoreDto store;
    String orderStatus;
    Float orderAmt;
    LocalDateTime orderTime;

    public static OrderDto of(Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .customer(CustomerDto.of(order.getCustomer()))
                .store(StoreDto.of(order.getStore()))
                .orderStatus(order.getOrderStatus())
                .orderAmt(order.getOrderAmt())
                .orderTime(order.getOrderTime())
                .build();
    }

    public Order toEntity() {
        return Order.builder()
                .id(this.id)
                .customer(this.customer.toEntity())
                .store(this.store.toEntity())
                .orderStatus(this.orderStatus)
                .orderAmt(this.orderAmt)
                .orderTime(this.orderTime)
                .build();
    }
}