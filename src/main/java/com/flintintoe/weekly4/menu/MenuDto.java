package com.flintintoe.weekly4.menu;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Menu}
 */
@Value
@AllArgsConstructor
@Builder
public class MenuDto implements Serializable {
    Integer id;
    String name;
    String category;
    String description;
    Float price;

    public static MenuDto of(Menu menu) {
        return MenuDto.builder()
                .id(menu.getId())
                .name(menu.getName())
                .category(menu.getCategory())
                .description(menu.getDescription())
                .price(menu.getPrice())
                .build();
    }

    public Menu toEntity() {
        return Menu.builder()
                .id(this.id)
                .name(this.name)
                .category(this.category)
                .description(this.description)
                .price(this.price)
                .build();
    }
}