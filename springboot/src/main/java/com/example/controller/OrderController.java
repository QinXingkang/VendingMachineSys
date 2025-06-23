package com.example.controller;

import com.example.entity.Beverage;

import com.example.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
        String[] decorators = ((java.util.List<String>) request.get("decorators"))
                .toArray(new String[0]);

        Beverage drink = shopService.processOrder(beverageName, decorators);

        Map<String, Object> response = new HashMap<>();
        response.put("description", drink.getDescription());
        response.put("cost", drink.getCost());
        return response;
    }
}
