package com.example.controller;

import com.example.common.Result;
import com.example.common.enums.ResultCode;
import com.example.entity.DecoratorItem;
import com.example.entity.Order;

import com.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author : QXK
 * @date : 2025-06-23 23:33
 * @description :
 */

@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "*")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public Result<Map<String, Object>> placeOrder(@RequestBody Map<String, Object> request) {
        try {
            String beverage = (String) request.get("beverage");
            // 安全转换 decorators
            Map<String, Integer> decorators = new HashMap<>();
            Object decoratorsObj = request.get("decorators");
            if (decoratorsObj instanceof Map) {
                Map<?, ?> map = (Map<?, ?>) decoratorsObj;
                for (Map.Entry<?, ?> entry : map.entrySet()) {
                    Object key = entry.getKey();
                    Object value = entry.getValue();
                    if (key instanceof String && value instanceof Number) {
                        decorators.put((String) key, ((Number) value).intValue());
                    }
                }
            }

            Order savedOrder = orderService.processOrder(beverage, decorators);

            Map<String, Object> response = new HashMap<>();
            response.put("orderId", savedOrder.getId());
            response.put("beverageId", savedOrder.getBeverage());
            response.put("beverageName", savedOrder.getBeverage().getName());
            response.put("totalCost", savedOrder.getTotalCost());
            response.put("description", savedOrder.getDescription());

            // 提取装饰明细
            List<Map<String, Object>> decoratorDetailList = savedOrder.getDecoratorDetails().stream()
                    .map(detail -> {
                        Map<String, Object> map = new HashMap<>();
                        map.put("id", detail.getDecorator().getId());
                        map.put("name", detail.getDecorator().getName());
                        map.put("quantity", detail.getQuantity());
                        return map;
                    }).toList();

            response.put("decorators", decoratorDetailList);

            return Result.success(response);
        }catch (IllegalArgumentException e){
            return Result.fail(ResultCode.BAD_REQUEST);
        }catch (Exception e){
            return Result.fail(ResultCode.INTERNAL_ERROR);
        }
    }

    @GetMapping("/history")
    public Result<List<Order>> getAllOrders() {
        try {
            List<Order> orders = orderService.getAllOrders(); // 你需要在 service 中实现
            return Result.success(orders);
        } catch (Exception e) {
            return Result.fail(ResultCode.INTERNAL_ERROR);
        }
    }
}
