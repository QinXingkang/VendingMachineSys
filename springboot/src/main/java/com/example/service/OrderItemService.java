package com.example.service;

import com.example.domain.Beverage;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import com.example.factory.*;

/**
 * @author : QXK
 * @date : 2025-06-25 14:56
 * @description :
 */
@Service
public class OrderItemService {
    public Map<String, Object> processOrder(String beverageName, Map<String, Integer> decoratorQuantities) {
        Beverage beverage = BeverageFactory.createBeverage(beverageName);

        for (Map.Entry<String, Integer> entry : decoratorQuantities.entrySet()) {
            beverage = DecoratorFactory.addDecorator(beverage, entry.getKey(), entry.getValue());
        }

        Map<String, Object> result = new HashMap<>();
        result.put("description", beverage.getDescription());
        result.put("totalCost", beverage.cost());

        return result;
    }
}
