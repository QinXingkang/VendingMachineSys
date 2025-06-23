package com.example.interfaces;

import com.example.entity.BeverageItem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : QXK
 * @date : 2025-06-24 1:08
 * @description :
 */
public interface BeverageItemRepository extends JpaRepository<BeverageItem, Long> {
    BeverageItem findByName(String name);
}
