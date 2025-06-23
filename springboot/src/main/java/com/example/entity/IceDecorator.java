package com.example.entity;


import com.example.interfaces.Beverage;

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