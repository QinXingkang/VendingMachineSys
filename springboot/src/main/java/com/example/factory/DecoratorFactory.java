package com.example.factory;


import com.example.domain.Beverage;
import com.example.interfaces.Factory;
import com.example.domain.IceDecorator;
import com.example.domain.MilkDecorator;
import org.springframework.stereotype.Component;

/**
 * @author : QXK
 * @date : 2025-06-23 15:49
 * @description :
 */
@Component
public class DecoratorFactory implements Factory {

    public static Beverage addDecorator(Beverage beverage, String decoratorName, int quantity) {
        for (int i = 0; i < quantity; i++) {
            beverage = switch (decoratorName.toLowerCase()) {
                case "ice" -> new IceDecorator(beverage);
                case "milk" -> new MilkDecorator(beverage);
                default -> throw new IllegalArgumentException("不支持的配料: " + decoratorName);
            };
        }
        return beverage;
    }
}
