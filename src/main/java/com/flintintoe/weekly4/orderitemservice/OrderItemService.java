package com.flintintoe.weekly4.orderitemservice;

import com.flintintoe.weekly4.menu.MenuDto;
import com.flintintoe.weekly4.orderitem.OrderItem;
import com.flintintoe.weekly4.orderitem.OrderItemDto;
import com.flintintoe.weekly4.store.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    @Transactional
    public OrderItemDto addOrderItem(OrderItemDto orderItemDto) {
        OrderItem order = orderItemDto.toEntity();
        OrderItem savedOrderItem = orderItemRepository.save(order);
        return OrderItemDto.of(savedOrderItem);
    }

    @Transactional
    public OrderItemDto createOrderItem(OrderItemDto orderItemDto) {
        OrderItem order = orderItemDto.toEntity();
        OrderItem savedOrderItem = orderItemRepository.save(order);
        return OrderItemDto.of(savedOrderItem);
    }

    @Transactional(readOnly = true)
    public List<OrderItemDto> getAllOrderItems() {
        return orderItemRepository.findAll().stream()
                .map(OrderItemDto::of)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<OrderItemDto> getOrderItemById(int id) {
        return orderItemRepository.findById(id)
                .map(OrderItemDto::of);
    }

    @Transactional
    public Optional<OrderItemDto> updateOrderItem(int id, OrderItemDto orderDto) {
        return orderItemRepository.findById(id)
                .map(existingOrderItem -> {
                    existingOrderItem.update(orderDto.getMenu().toEntity(), orderDto.getOrder().toEntity(), orderDto.getSalePrice());
                    return orderDto.of(orderItemRepository.save(existingOrderItem));
                });
    }

    @Transactional
    public boolean deleteOrderItem(int id) {
        return orderItemRepository.findById(id)
                .map(orderItem -> {
                    orderItemRepository.delete(orderItem);
                    return true;
                })
                .orElse(false);
    }

    @Transactional(readOnly = true)
    public List<Object> getTop3MenuItems() {
        return orderItemRepository.findTop3MenuItems();
    }
}
