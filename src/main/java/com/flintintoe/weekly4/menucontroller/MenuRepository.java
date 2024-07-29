package com.flintintoe.weekly4.menucontroller;

import com.flintintoe.weekly4.menu.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {
    @Query("SELECT b FROM Menu b WHERE b.category = :category")
    List<Menu> findByCategory(@Param("category") String category);
}
