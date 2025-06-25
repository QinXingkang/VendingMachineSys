package com.example.entity;

import com.example.domain.Beverage;

/**
 * @author : QXK
 * @date : 2025-06-23 15:59
 * @description :
 */
public class NoBeverage extends Beverage {
    String description = "没有您所点的饮料，请重新点(coca 或 coffee)。";
    public String getDescription(){
        return description;
    }
    public double getCost() {
        return 0;
    }

    @Override
    public double cost() {
        return 0;
    }
}
