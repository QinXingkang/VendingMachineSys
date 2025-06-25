package com.example.dto;

import com.example.entity.BeverageItem;
import com.example.entity.DecoratorItem;

import java.util.List;

/**
 * @author : QXK
 * @date : 2025-06-25 9:40
 * @description :
 */
public class MessageDTO {
    private List<BeverageItem> beverages;
    private List<DecoratorItem> decorators;

    public MessageDTO(List<BeverageItem> beverages, List<DecoratorItem> decorators) {
        this.beverages = beverages;
        this.decorators = decorators;
    }

    public List<BeverageItem> getBeverages() {
        return beverages;
    }

    public void setBeverages(List<BeverageItem> beverages) {
        this.beverages = beverages;
    }

    public List<DecoratorItem> getDecorators() {
        return decorators;
    }

    public void setDecorators(List<DecoratorItem> decorators) {
        this.decorators = decorators;
    }
}
