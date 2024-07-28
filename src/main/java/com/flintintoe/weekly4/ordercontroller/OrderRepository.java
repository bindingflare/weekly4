package com.flintintoe.weekly4.ordercontroller;

import com.flintintoe.weekly4.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
