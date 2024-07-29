package com.flintintoe.weekly4.storecontroller;

import com.flintintoe.weekly4.menu.MenuDto;
import com.flintintoe.weekly4.store.Store;
import com.flintintoe.weekly4.store.StoreDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StoreService {
    private final StoreRepository storeRepository;
    
    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Transactional
    public StoreDto addStore(StoreDto storeDto) {
        Store store = storeDto.toEntity();
        Store savedStore = storeRepository.save(store);
        return StoreDto.of(savedStore);
    }

    @Transactional
    public StoreDto createStore(StoreDto storeDto) {
        Store store = storeDto.toEntity();
        Store savedStore = storeRepository.save(store);
        return StoreDto.of(savedStore);
    }

    @Transactional(readOnly = true)
    public List<StoreDto> getAllStores() {
        return storeRepository.findAll().stream()
                .map(StoreDto::of)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<StoreDto> getStoreById(int id) {
        return storeRepository.findById(id)
                .map(StoreDto::of);
    }

    @Transactional
    public Optional<StoreDto> updateStore(int id, StoreDto storeDto) {
        return storeRepository.findById(id)
                .map(existingStore -> {
                    existingStore.update(storeDto.getName(), storeDto.getAddress(), storeDto.getPhoneNumber());
                    return StoreDto.of(storeRepository.save(existingStore));
                });
    }

    @Transactional
    public boolean deleteOrder(int id) {
        return storeRepository.findById(id)
                .map(store -> {
                    storeRepository.delete(store);
                    return true;
                })
                .orElse(false);
    }
}
