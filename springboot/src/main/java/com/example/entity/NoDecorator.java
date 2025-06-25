package com.example.entity;

import com.example.domain.Beverage;
import com.example.domain.Decorator;

/**
 * @author : QXK
 * @date : 2025-06-23 15:59
 * @description :
 */
public class NoDecorator extends Decorator {
    String description = "没有您所点的配料，请重新点(milk 或 ice)。";
    public String getDescription() {
        return this.description;
    }
    public double getCost() {
        return 0;
    }

    public NoDecorator(Beverage beverage) {
        super(beverage);
    }

    @Override
    public double cost() {
        return 0;
    }
}
