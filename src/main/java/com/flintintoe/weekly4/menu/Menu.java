package com.flintintoe.weekly4.menu;

import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "menu", schema = "restaurant")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @Column(name = "category", nullable = false, length = 32)
    private String category;

    @Column(name = "description", length = 256)
    private String description;

    @Column(name = "price", nullable = false)
    private Float price;

    public void update(String name, String category, String description, Float price) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.price = price;
    }
}