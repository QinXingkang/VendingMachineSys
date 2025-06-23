package com.example.factory;

import com.example.interfaces.Beverage;
import com.example.entity.Coca;
import com.example.entity.Coffee;
import com.example.interfaces.Factory;
import com.example.entity.NoBeverage;
import org.springframework.stereotype.Component;

/**
 * @author : QXK
 * @date : 2025-06-23 15:49
 * @description :
 */
@Component
public class BeverageFactory implements Factory {
    public static Beverage createBeverage(String name) {
        if ("Coca".equalsIgnoreCase(name)) {
            return new Coca();
        } else if ("Coffee".equalsIgnoreCase(name)) {
            return new Coffee();
        } else {
            return new NoBeverage();
        }
    }
}
