package com.example.entity;

/**
 * @author : QXK
 * @date : 2025-06-23 15:59
 * @description :
 */
public class NoBeverage implements Beverage {
    String description = "没有您所点的饮料，请重新点(coca 或 coffee)。";
    public String getDescription(){
        return description;
    }
    public double getCost() {
        return 0;
    }
}
