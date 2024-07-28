package com.flintintoe.weekly4.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Customer}
 */
@Value
@AllArgsConstructor
@Builder
public class CustomerDto implements Serializable {
    Integer id;
    String customerName;
    String phoneNumber;
    String address;

    public static CustomerDto of(Customer customer) {
        return CustomerDto.builder()
                .id(customer.getId())
                .customerName(customer.getCustomerName())
                .phoneNumber(customer.getPhoneNumber())
                .address(customer.getAddress()).build();
    }

    public Customer toEntity() {
        return Customer.builder()
                .id(this.id)
                .customerName(this.customerName)
                .phoneNumber(this.phoneNumber)
                .address(this.address).build();
    }
}