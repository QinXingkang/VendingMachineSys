package com.example.controller;

import com.example.common.Result;
import com.example.common.enums.ResultCode;
import com.example.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : QXK
 * @date : 2025-06-25 14:58
 * @description :
 */
@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderItemController {
    @Autowired
    private OrderItemService orderItemService;

    @PostMapping("/front")
    public Result<Map<String, Object>> placeOrder(@RequestBody Map<String, Object> request) {
        try {
            String beverage = (String) request.get("beverage");
            Map<String, Integer> decorators = new HashMap<>();

            Object decoObj = request.get("decorators");
            if (decoObj instanceof Map<?, ?> map) {
                for (Map.Entry<?, ?> entry : map.entrySet()) {
                    if (entry.getKey() instanceof String key && entry.getValue() instanceof Number value) {
                        decorators.put(key, value.intValue());
                    }
                }
            }

            Map<String, Object> result = orderItemService.processOrder(beverage, decorators);
            return Result.success(result);

        } catch (Exception e) {
            return Result.fail(ResultCode.INTERNAL_ERROR);
        }
    }
}
