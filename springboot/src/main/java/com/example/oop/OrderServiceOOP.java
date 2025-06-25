package com.example.oop;

import com.example.domain.Beverage;
import com.example.factory.BeverageFactory;
import com.example.factory.DecoratorFactory;

/**
 * @author : QXK
 * @date : 2025-06-25 14:53
 * @description :
 */
public class OrderServiceOOP {
    public static void main(String[] args) {
        // 假设用户选择 Coffee，加 2 份 Milk，1 份 Ice
        Beverage beverage = BeverageFactory.createBeverage("Coffee");

        beverage = DecoratorFactory.addDecorator(beverage, "Milk", 2);
        beverage = DecoratorFactory.addDecorator(beverage, "Ice", 1);

        System.out.println("描述: " + beverage.getDescription());
        System.out.println("总价: ¥" + beverage.cost());
    }
}
