package com.flintintoe.weekly4.store;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Store}
 */
@Value
public class StoreDto implements Serializable {
    Integer id;
    String name;
    String address;
    String phoneNumber;
}