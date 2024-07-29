package com.flintintoe.weekly4.storecontroller;

import com.flintintoe.weekly4.menu.MenuDto;
import com.flintintoe.weekly4.menucontroller.MenuService;
import com.flintintoe.weekly4.ordercontroller.OrderService;
import com.flintintoe.weekly4.orderitemservice.OrderItemService;
import com.flintintoe.weekly4.store.StoreDto;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/store")
public class StoreController {
    private final StoreService storeService;
    private final OrderService orderService;
    private final OrderItemService orderItemService;

    public StoreController(StoreService storeService, OrderService orderService, OrderItemService orderItemService) {
        this.storeService = storeService;
        this.orderService = orderService;
        this.orderItemService = orderItemService;
    }

    @PostMapping
    public ResponseEntity<StoreDto> createStore(@RequestBody StoreDto orderDto) {
        StoreDto createdOrder = storeService.createStore(orderDto);
        return ResponseEntity.ok(createdOrder);
    }

    @GetMapping
    public ResponseEntity<List<StoreDto>> getAllStores() {
        List<StoreDto> orders = storeService.getAllStores();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoreDto> getStore(@PathVariable int id) {
        return storeService.getStoreById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/sales/{storeId}")
    public ResponseEntity<Object> getSalesForStore(@PathVariable int storeId, @RequestParam("startDate") LocalDateTime startDate, @RequestParam("endDate") LocalDateTime endDate) {
        StoreDto store = storeService.getStoreById(storeId).get();
        log.info("id = {}",store.getId());
        Object sales = orderService.getSalesOfStore(store.toEntity(), startDate, endDate);
        log.info("sales = {}", sales);
        return ResponseEntity.ok(sales);
    }

    @GetMapping("/top3")
    public ResponseEntity<List<Object>> getTop3MenuItems() {
        List<Object> menus = orderItemService.getTop3MenuItems();
        return ResponseEntity.ok(menus);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StoreDto> updateStore(@PathVariable int id, @RequestBody StoreDto orderDto) {
        return storeService.updateStore(id, orderDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStore(@PathVariable int id) {
        boolean deleted = storeService.deleteOrder(id);
        return deleted? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
