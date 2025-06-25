package com.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * @author : QXK
 * @date : 2025-06-24 1:01
 * @description :
 */
@Entity
@Table(name = "beverage_item") // 避免与接口重名
public class BeverageItem {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private Double price;
//    private Long stock;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
//
//    public Long getStock() {
//        return stock;
//    }
//
//    public void setStock(Long stock) {
//        this.stock = stock;
//    }
}
