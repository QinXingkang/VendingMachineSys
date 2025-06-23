package com.example.service;

import com.example.entity.Order;
import com.example.factory.BeverageFactory;
import com.example.factory.DecoratorFactory;
import com.example.interfaces.Beverage;
import com.example.interfaces.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ShopService {

    @Autowired
    private OrderRepository orderRepository;

    public Order processOrder(String beverageName, String[] decorators) {
        Beverage drink = BeverageFactory.createBeverage(beverageName);
        for (String decorator : decorators) {
            drink = DecoratorFactory.addDecorator(drink, decorator);
        }

        //实现数据库持久化
        Order order = new Order();
        order.setBeverageName(beverageName);
        order.setDecorators(Arrays.asList(decorators));
        order.setTotalCost(drink.getCost());
        orderRepository.save(order);

        return order;
    }


}