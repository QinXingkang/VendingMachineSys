package com.example.domain;

public class Coca extends Beverage {
    @Override
    public String getDescription() {
        return "Coca";
    }

    @Override
    public double cost() {
        return 3.0;
    }
}