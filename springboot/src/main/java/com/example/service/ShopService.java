package com.example.service;

import com.example.factory.BeverageFactory;
import com.example.factory.DecoratorFactory;
import com.example.entity.Beverage;
import org.springframework.stereotype.Service;

@Service
public class ShopService {
    public Beverage processOrder(String beverageName, String[] decorators) {
        Beverage drink = BeverageFactory.createBeverage(beverageName);
        for (String decorator : decorators) {
            drink = DecoratorFactory.addDecorator(drink, decorator);
        }
        return drink;
    }
}