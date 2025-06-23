package com.example.entity;



public abstract class Decorator implements Beverage {
    protected Beverage beverage;

    public Decorator(Beverage beverage) {
        this.beverage = beverage;
    }

    public Decorator() {
    }
}