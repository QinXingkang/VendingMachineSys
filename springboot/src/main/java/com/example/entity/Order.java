package com.example.entity;

import jakarta.persistence.*;

import java.util.List;

/**
 * @author : QXK
 * @date : 2025-06-24 0:26
 * @description :
 */

/**
 * 1.数据库orders表的订单id与orders_seq中的next_val对不上，且next_val变化很大的原因
 *  这是JPA使用的序列生成策略的正常行为，背后是出于性能优化的考虑
 *  Hibernate通常会使用一种 "hi/lo" 或者 pooled（池化）策略来预取 ID 值段
 *  等预取的一段用完了再更新一次orders_seq中的next_val
 */
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    // 关联饮料实体（一个订单只对应一个饮料）
    @ManyToOne
    @JoinColumn(name = "beverage_id")
    private BeverageItem beverage;

    private String beverageName;

    //配料ids
    private String decoratorIds;

    //配料名
    private String decoratorNames;

    private Double totalCost;

    private String description;

    //getter、setter

    public String getDecoratorIds() {
        return decoratorIds;
    }

    public void setDecoratorIds(String decoratorIds) {
        this.decoratorIds = decoratorIds;
    }

    public String getDecoratorNames() {
        return decoratorNames;
    }

    public void setDecoratorNames(String decoratorNames) {
        this.decoratorNames = decoratorNames;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BeverageItem getBeverage() {
        return beverage;
    }

    public void setBeverage(BeverageItem beverage) {
        this.beverage = beverage;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public String getBeverageName() {
        return beverageName;
    }

    public void setBeverageName(String beverageName) {
        this.beverageName = beverageName;
    }
}
