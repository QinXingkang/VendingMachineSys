package com.example.domain;

public class Coffee extends Beverage {
    @Override
    public String getDescription() {
        return "Coffee";
    }

    @Override
    public double cost() {
        return 4.0;
    }
}