package com.flintintoe.weekly4.orderitemservice;

import com.flintintoe.weekly4.menu.MenuDto;
import com.flintintoe.weekly4.orderitem.OrderItem;
import com.flintintoe.weekly4.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    @Query(value = "SELECT oi.menu, COUNT(oi.menu) as quantity FROM OrderItem oi GROUP BY oi.menu ORDER BY quantity DESC limit 3")
    List<Object> findTop3MenuItems();
}
