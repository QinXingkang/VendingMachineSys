package com.example.controller;

import com.example.common.Result;
import com.example.dto.MessageDTO;
import com.example.entity.BeverageItem;
import com.example.entity.DecoratorItem;
import com.example.interfaces.BeverageItemRepository;
import com.example.interfaces.DecoratorItemRepository;
import com.example.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : QXK
 * @date : 2025-06-25 9:41
 * @description :
 */
@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/detail")
    public Result<MessageDTO> getAllBeverageMessage() {
        return Result.success(messageService.getAllMessage());
    }
}
