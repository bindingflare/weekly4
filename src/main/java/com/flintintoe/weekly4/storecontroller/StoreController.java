package com.flintintoe.weekly4.storecontroller;

import com.flintintoe.weekly4.store.StoreDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/store")
public class StoreController {
    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
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
