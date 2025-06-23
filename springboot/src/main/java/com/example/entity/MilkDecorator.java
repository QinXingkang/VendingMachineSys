package com.example.entity;


import com.example.interfaces.Beverage;

public class MilkDecorator extends Decorator {
    public MilkDecorator(Beverage beverage) {
        super(beverage);
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + " + Milk";
    }

    @Override
    public double getCost() {
        return beverage.getCost() + 1.0;
    }
}