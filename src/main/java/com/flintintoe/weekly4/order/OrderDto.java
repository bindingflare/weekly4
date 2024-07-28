package com.flintintoe.weekly4.order;

import com.flintintoe.weekly4.customer.CustomerDto;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Order}
 */
@Value
public class OrderDto implements Serializable {
    Integer id;
    CustomerDto customer;
    String orderStatus;
    Float orderAmt;
}