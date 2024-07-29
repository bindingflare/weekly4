package com.flintintoe.weekly4.orderitemservice;

import com.flintintoe.weekly4.customer.CustomerDto;
import com.flintintoe.weekly4.orderitem.OrderItemDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderitem")
public class OrderItemController {
    private final OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @PostMapping
    public ResponseEntity<OrderItemDto> createOrder(@RequestBody OrderItemDto orderDto) {
        OrderItemDto createdOrder = orderItemService.createOrderItem(orderDto);
        return ResponseEntity.ok(createdOrder);
    }

    @GetMapping
    public ResponseEntity<List<OrderItemDto>> getAllOrders() {
        List<OrderItemDto> orders = orderItemService.getAllOrderItems();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItemDto> getOrder(@PathVariable int id) {
        return orderItemService.getOrderItemById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderItemDto> updateOrder(@PathVariable int id, @RequestBody OrderItemDto orderDto) {
        return orderItemService.updateOrderItem(id, orderDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable int id) {
        boolean deleted = orderItemService.deleteOrderItem(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
