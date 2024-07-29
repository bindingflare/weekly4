package com.flintintoe.weekly4.ordercontroller;

import com.flintintoe.weekly4.order.Order;
import com.flintintoe.weekly4.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query("SELECT SUM(o.orderAmt) as sales FROM Order o WHERE o.store = :store AND o.orderStatus = 'ORDER_COMPLETE' AND o.orderTime BETWEEN :start_time AND :end_time")
    Object findSalesOfStore(@Param("store") Store store, @Param("start_time") LocalDateTime startTime, @Param("end_time") LocalDateTime endTime);
}
