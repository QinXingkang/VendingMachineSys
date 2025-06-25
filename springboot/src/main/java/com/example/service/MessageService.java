package com.example.service;

import com.example.dto.MessageDTO;
import com.example.interfaces.BeverageItemRepository;
import com.example.interfaces.DecoratorItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : QXK
 * @date : 2025-06-25 9:49
 * @description :
 */
@Service
public class MessageService {
    @Autowired
    private BeverageItemRepository beverageRepository;

    @Autowired
    private DecoratorItemRepository decoratorRepository;

    public MessageDTO getAllMessage() {
        return new MessageDTO(
                beverageRepository.findAll(),
                decoratorRepository.findAll()
        );
    }
}
