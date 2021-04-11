package org.example.services;

import org.example.dto.Item;

import java.util.Map;

public class TraderService {

    private AppService appService;

    private Map<String, Item> traderInventory;

    public TraderService(AppService appService) {
        this.appService = appService;
    }
}
