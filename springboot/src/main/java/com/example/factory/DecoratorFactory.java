package com.example.factory;


import com.example.entity.Beverage;
import com.example.entity.Factory;
import com.example.entity.IceDecorator;
import com.example.entity.MilkDecorator;
import com.example.entity.NoDecorator;
import org.springframework.stereotype.Component;

/**
 * @author : QXK
 * @date : 2025-06-23 15:49
 * @description :
 */
@Component
public class DecoratorFactory implements Factory {

    public static Beverage addDecorator(Beverage beverage, String decoratorName) {
        if ("Milk".equalsIgnoreCase(decoratorName)) {
            return new MilkDecorator(beverage);
        } else if ("Ice".equalsIgnoreCase(decoratorName)) {
            return new IceDecorator(beverage);
        } else {
            return new NoDecorator(beverage);
        }
    }
}
