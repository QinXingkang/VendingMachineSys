package com.example.interfaces;

import com.example.entity.OrderDecoratorDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * @author : QXK
 * @date : 2025-06-24 9:37
 * @description :
 */
public interface OrderDecoratorDetailRepository extends JpaRepository<OrderDecoratorDetail, Long> {
}
