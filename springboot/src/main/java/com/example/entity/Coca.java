package com.example.entity;

import com.example.interfaces.Beverage;

public class Coca implements Beverage {
    @Override
    public String getDescription() {
        return "Coca";
    }

    @Override
    public double getCost() {
        return 1.0;
    }
}