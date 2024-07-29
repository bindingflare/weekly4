package com.flintintoe.weekly4.ordercontroller;

import com.flintintoe.weekly4.order.Order;
import com.flintintoe.weekly4.order.OrderDto;

import com.flintintoe.weekly4.store.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository menuRepository) {
        this.orderRepository = menuRepository;
    }

    @Transactional
    public OrderDto addOrder(OrderDto OrderDto) {
        Order order = OrderDto.toEntity();
        Order savedOrder = orderRepository.save(order);
        return OrderDto.of(savedOrder);
    }

    @Transactional
    public OrderDto createOrder(OrderDto orderDto) {
        Order order = orderDto.toEntity();
        order.updateTime(); // UPDATE TIMESTAMP
        Order savedOrder = orderRepository.save(order);
        return OrderDto.of(savedOrder);
    }

    @Transactional(readOnly = true)
    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(OrderDto::of)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<OrderDto> getOrderById(int id) {
        return orderRepository.findById(id)
                .map(OrderDto::of);
    }

    @Transactional
    public Optional<OrderDto> updateOrder(int id, OrderDto orderDto) {
        return orderRepository.findById(id)
                .map(existingOrder -> {
                    existingOrder.update(orderDto.getCustomer().toEntity(), orderDto.getStore().toEntity(), orderDto.getOrderStatus(), orderDto.getOrderAmt(), orderDto.getOrderTime());
                    existingOrder.updateTime(); // UPDATE TIMESTAMP
                    return orderDto.of(orderRepository.save(existingOrder));
                });
    }

    @Transactional
    public boolean deleteOrder(int id) {
        return orderRepository.findById(id)
                .map(order -> {
                    orderRepository.delete(order);
                    return true;
                })
                .orElse(false);
    }

    @Transactional(readOnly = true)
    public Object getSalesOfStore(Store store, LocalDateTime from, LocalDateTime to) {
        return orderRepository.findSalesOfStore(store, from, to);
    }
}
