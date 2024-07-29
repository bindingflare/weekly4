package com.flintintoe.weekly4.orderitemservice;

import com.flintintoe.weekly4.order.OrderDto;
import com.flintintoe.weekly4.ordercontroller.OrderService;
import com.flintintoe.weekly4.orderitem.OrderItemDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/orderitem")
public class OrderItemController {
    private final OrderItemService orderItemService;
    private final OrderService orderService;

    public OrderItemController(OrderItemService orderItemService, OrderService orderService) {
        this.orderItemService = orderItemService;
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderItemDto> createOrderItem(@RequestBody OrderItemDto orderItemDto) {
        log.info("Creating order item: {}", orderItemDto);
        Optional<OrderDto> orderById = orderService.getOrderById(orderItemDto.getOrder().getId());
        OrderDto orderDto = orderById.stream().findFirst().orElse(null); // populate order information to prevent NULL POINTER


        OrderItemDto saveOrderItem = OrderItemDto.builder()
                .id(orderItemDto.getId())
                .menu(orderItemDto.getMenu())
                .order(orderDto)
                .salePrice(orderItemDto.getSalePrice())
                .build();
        OrderItemDto createdOrderItem = orderItemService.createOrderItem(saveOrderItem);
        return ResponseEntity.ok(createdOrderItem);
    }

    @GetMapping
    public ResponseEntity<List<OrderItemDto>> getAllOrderItems() {
        List<OrderItemDto> orders = orderItemService.getAllOrderItems();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItemDto> getOrderItem(@PathVariable int id) {
        return orderItemService.getOrderItemById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderItemDto> updateOrderItem(@PathVariable int id, @RequestBody OrderItemDto orderItemDto) {
        return orderItemService.updateOrderItem(id, orderItemDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable int id) {
        boolean deleted = orderItemService.deleteOrderItem(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
