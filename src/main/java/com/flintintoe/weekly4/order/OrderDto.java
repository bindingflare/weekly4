package com.flintintoe.weekly4.order;

import com.flintintoe.weekly4.customer.CustomerDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Order}
 */
@Value
@AllArgsConstructor
@Builder
public class OrderDto implements Serializable {
    Integer id;
    CustomerDto customer;
    String orderStatus;
    Float orderAmt;

    public static OrderDto of(Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .customer(CustomerDto.of(order.getCustomer()))
                .orderStatus(order.getOrderStatus())
                .orderAmt(order.getOrderAmt())
                .build();
    }

    public Order toEntity() {
        return Order.builder()
                .id(this.id)
                .customer(this.customer.toEntity())
                .orderStatus(this.orderStatus)
                .orderAmt(this.orderAmt)
                .build();
    }
}