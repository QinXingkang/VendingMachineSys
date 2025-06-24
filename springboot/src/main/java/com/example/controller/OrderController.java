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
@CrossOrigin // 允许前端跨域请求
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public Result<Map<String, Object>> placeOrder(@RequestBody Map<String, Object> request) {
        try {
            String beverage = (String) request.get("beverage");
            List<String> decorators = (List<String>) request.get("decorators");

            Order savedOrder = orderService.processOrder(beverage, decorators);

            Map<String, Object> response = new HashMap<>();
            response.put("orderId", savedOrder.getId());
            response.put("beverageId", savedOrder.getBeverage());
            response.put("beverageName", savedOrder.getBeverage().getName());
            response.put("decoratorIds", savedOrder.getDecoratorIds());
            response.put("decoratorNames", savedOrder.getDecoratorNames());
            response.put("totalCost", savedOrder.getTotalCost());
            response.put("description", savedOrder.getDescription());

            return Result.success(response);
        }catch (IllegalArgumentException e){
            return Result.fail(ResultCode.BAD_REQUEST);
        }catch (Exception e){
            return Result.fail(ResultCode.INTERNAL_ERROR);
        }


    }
}
