package com.example.entity;

import jakarta.persistence.*;

/**
 * @author : QXK
 * @date : 2025-06-24 9:35
 * @description :
 */
@Entity
@Table(name = "order_decorator_detail")
public class OrderDecoratorDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "decorator_id")
    private DecoratorItem decorator;

    private int quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public DecoratorItem getDecorator() {
        return decorator;
    }

    public void setDecorator(DecoratorItem decorator) {
        this.decorator = decorator;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
