package com.example.entity;


public class IceDecorator extends Decorator {
    public IceDecorator(Beverage beverage) {
        super(beverage);
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + " + Ice";
    }

    @Override
    public double getCost() {
        return beverage.getCost() + 0.5;
    }
}