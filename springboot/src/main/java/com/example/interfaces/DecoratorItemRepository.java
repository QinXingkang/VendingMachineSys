package com.example.interfaces;


import com.example.entity.DecoratorItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author : QXK
 * @date : 2025-06-24 1:09
 * @description :
 */
public interface DecoratorItemRepository extends JpaRepository<DecoratorItem, Integer> {
    List<DecoratorItem> findByNameIn(List<String> names);

}
