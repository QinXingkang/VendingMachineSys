package com.example.entity;

import jakarta.persistence.*;

import java.util.List;

/**
 * @author : QXK
 * @date : 2025-06-24 0:26
 * @description :
 */
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String beverageName;

    @ElementCollection
    @CollectionTable(name = "order_decorators", joinColumns = @JoinColumn(name = "order_id"))
    @Column(name = "decorator")
    private List<String> decorators;

    private Double totalCost;

    //getter、setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBeverageName() {
        return beverageName;
    }

    public void setBeverageName(String beverageName) {
        this.beverageName = beverageName;
    }

    public List<String> getDecorators() {
        return decorators;
    }

    public void setDecorators(List<String> decorators) {
        this.decorators = decorators;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }
}
