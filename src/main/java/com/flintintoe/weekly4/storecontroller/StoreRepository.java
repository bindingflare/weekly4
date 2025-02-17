package com.flintintoe.weekly4.storecontroller;

import com.flintintoe.weekly4.menu.MenuDto;
import com.flintintoe.weekly4.store.Store;
import com.flintintoe.weekly4.store.StoreDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, Integer> {
}
