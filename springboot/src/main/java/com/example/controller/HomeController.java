package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : QXK
 * @date : 2025-06-23 23:50
 * @description : 后端页面测试Controller
 */

@RestController
public class HomeController {
    @GetMapping("/")
    public String index() {
        return "Welcome to the Vending Machine API!";
    }
}