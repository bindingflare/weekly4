package com.flintintoe.weekly4.orderitem;

import com.flintintoe.weekly4.menu.MenuDto;
import com.flintintoe.weekly4.order.OrderDto;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link OrderItem}
 */
@Value
public class OrderItemDto implements Serializable {
    Integer id;
    MenuDto menu;
    OrderDto order;
    Float salePrice;
}