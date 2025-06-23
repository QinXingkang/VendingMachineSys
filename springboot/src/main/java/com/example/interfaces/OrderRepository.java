package com.example.interfaces;

import com.example.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : QXK
 * @date : 2025-06-24 0:29
 * @description :
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
}
