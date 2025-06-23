package com.example.service;

import com.example.entity.BeverageItem;
import com.example.entity.DecoratorItem;
import com.example.entity.Order;
import com.example.factory.BeverageFactory;
import com.example.factory.DecoratorFactory;
import com.example.interfaces.Beverage;
import com.example.interfaces.BeverageItemRepository;
import com.example.interfaces.DecoratorItemRepository;
import com.example.interfaces.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private BeverageItemRepository beverageRepo;

    @Autowired
    private DecoratorItemRepository decoratorRepo;

    @Autowired
    private OrderRepository orderRepo;


    public Order processOrder(String beverageName, List<String> decoratorNames) {
        // 查询饮料
        BeverageItem beverage = beverageRepo.findByName(beverageName);
        if (beverage == null) {
            throw new IllegalArgumentException("饮料名称不存在: " + beverageName);
        }

        // 查询配料
        List<DecoratorItem> decorators = decoratorRepo.findByNameIn(decoratorNames);

        // 计算总价
        double totalCost = beverage.getPrice();
        for (DecoratorItem item : decorators) {
            totalCost += item.getPrice();
        }

        // 拼接描述
        String description = beverage.getName();
        if (!decorators.isEmpty()) {
            description += " + " + decorators.stream()
                    .map(DecoratorItem::getName)
                    .reduce((a, b) -> a + " + " + b)
                    .orElse("");
        }

        // 构建订单
        Order order = new Order();
        order.setBeverage(beverage);
        order.setBeverageName(beverageName);
        order.setDecoratorIds(decorators.stream().map(d -> d.getId().toString()).collect(Collectors.joining(",")));
        order.setDecoratorNames(decorators.stream().map(DecoratorItem::getName).collect(Collectors.joining(",")));
        order.setTotalCost(totalCost);
        order.setDescription(description);
        return orderRepo.save(order);
    }


}