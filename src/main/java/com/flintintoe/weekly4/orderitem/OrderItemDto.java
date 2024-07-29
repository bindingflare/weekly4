package com.flintintoe.weekly4.orderitem;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.flintintoe.weekly4.customer.CustomerDto;
import com.flintintoe.weekly4.menu.MenuDto;
import com.flintintoe.weekly4.order.Order;
import com.flintintoe.weekly4.order.OrderDto;
import com.flintintoe.weekly4.store.StoreDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link OrderItem}
 */
@Value
@AllArgsConstructor
@Builder
public class OrderItemDto implements Serializable {
    Integer id;
    MenuDto menu;
    OrderDto order;
    Float salePrice;

    public static OrderItemDto of(OrderItem orderItem) {
        return OrderItemDto.builder()
                .id(orderItem.getId())
                .menu(MenuDto.of(orderItem.getMenu()))
                .order(OrderDto.of(orderItem.getOrder()))
                .salePrice(orderItem.getSalePrice())
                .build();
    }

    public OrderItem toEntity() {
        return OrderItem.builder()
                .id(this.id)
                .menu(this.menu.toEntity())
                .order(this.order.toEntity())
                .salePrice(this.salePrice)
                .build();
    }
}