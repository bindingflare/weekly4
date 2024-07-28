package com.flintintoe.weekly4.store;

import com.flintintoe.weekly4.customer.Customer;
import com.flintintoe.weekly4.customer.CustomerDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Store}
 */
@Value
@AllArgsConstructor
@Builder
public class StoreDto implements Serializable {
    Integer id;
    String name;
    String address;
    String phoneNumber;

    public static StoreDto of(Store store) {
        return StoreDto.builder()
                .id(store.getId())
                .name(store.getName())
                .address(store.getAddress())
                .phoneNumber(store.getPhoneNumber())
                .build();
    }

    public Store toEntity() {
        return Store.builder()
                .id(this.id)
                .name(this.name)
                .address(this.address)
                .phoneNumber(this.phoneNumber)
                .build();
    }
}