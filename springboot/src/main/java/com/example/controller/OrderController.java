package com.example.controller;

import com.example.entity.Order;
import com.example.interfaces.Beverage;

import com.example.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private ShopService shopService;

    @PostMapping
    public Map<String, Object> processOrder(@RequestBody Map<String, Object> request) {
        String beverageName = (String) request.get("beverage");
        String[] decorators = ((List<String>) request.get("decorators"))
                .toArray(new String[0]);

        Order order = shopService.processOrder(beverageName,decorators);

        Map<String, Object> response = new HashMap<>();
        response.put("orderId", order.getId());
        response.put("description", beverageName + " + " + String.join(", ", order.getDecorators()));
        response.put("cost", order.getTotalCost());
        return response;
    }
}
