package com.example.service;

import com.example.entity.*;
import com.example.factory.BeverageFactory;
import com.example.factory.DecoratorFactory;
import com.example.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private BeverageItemRepository beverageRepo; //用于查询和操作

    @Autowired
    private DecoratorItemRepository decoratorRepo; //用于查询和操作

    @Autowired
    private OrderRepository orderRepo; //用于保存订单数据

    @Autowired
    private OrderDecoratorDetailRepository orderDecoratorDetailRepo;


    public Order processOrder(String beverageName, Map<String, Integer> decoratorQuantities) {
        // 查询饮料
        BeverageItem beverage = beverageRepo.findByName(beverageName);
        if (beverage.getName() == null) {
            throw new IllegalArgumentException("饮料名称不存在: " + beverageName);
        }

        // 查询所有选中的配料
        List<String> decoratorNames = new ArrayList<>(decoratorQuantities.keySet());
        List<DecoratorItem> decorators = decoratorRepo.findByNameIn(decoratorNames);

        // 计算总价
        double totalCost = beverage.getPrice();
        for (DecoratorItem decorator : decorators) {
            int quantity = decoratorQuantities.getOrDefault(decorator.getName(), 0);
            totalCost += decorator.getPrice() * quantity;
        }

        // 构建订单实体
        Order order = new Order();
        order.setBeverage(beverage);
        order.setBeverageName(beverage.getName());
        order.setTotalCost(totalCost);

        // 构造描述字符串
        String description = beverage.getName();
        if (!decorators.isEmpty()) {
            description += " + " + decorators.stream()
                    .map(d -> d.getName() + " x" + decoratorQuantities.getOrDefault(d.getName(), 0))
                    .reduce((a, b) -> a + " + " + b)
                    .orElse("");
        }
        order.setDescription(description);

        // 保存订单（先保存Order，获得orderId）
        order = orderRepo.save(order);

        // 保存配料明细（OrderDecoratorDetail）
        List<OrderDecoratorDetail> detailList = new ArrayList<>();
        for (DecoratorItem decorator : decorators) {
            Integer quantity = decoratorQuantities.get(decorator.getName());
            if (quantity != null && quantity > 0) {
                OrderDecoratorDetail detail = new OrderDecoratorDetail();
                detail.setOrder(order);
                detail.setDecorator(decorator);
                detail.setQuantity(quantity);
                orderDecoratorDetailRepo.save(detail);
                detailList.add(detail);
            }
        }

        order.setDecoratorDetails(detailList);

        return order;
    }

    public List<Order> getAllOrders() {
        return orderRepo.findAll(); // 如果你是用 JPA / MyBatis，可以自定义
    }


}