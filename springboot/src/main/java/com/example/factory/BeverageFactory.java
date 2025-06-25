package com.example.factory;

import com.example.domain.Beverage;
import com.example.domain.Coca;
import com.example.domain.Coffee;
import com.example.interfaces.Factory;
import org.springframework.stereotype.Component;

/**
 * @author : QXK
 * @date : 2025-06-23 15:49
 * @description :
 */
@Component
public class BeverageFactory implements Factory {
    public static Beverage createBeverage(String name) {
        return switch (name.toLowerCase()) {
            case "coca" -> new Coca();
            case "coffee" -> new Coffee();
            default -> throw new IllegalArgumentException("不支持的饮品类型: " + name);
        };
    }

}
