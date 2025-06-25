package com.example.domain;


public class MilkDecorator extends Decorator {
    public MilkDecorator(Beverage beverage) {
        super(beverage);
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + " + Milk";
    }

    @Override
    public double cost() {
        return beverage.cost() + 1.0;
    }
}