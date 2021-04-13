package org.example.services;

import javafx.scene.control.Label;

public class GoldService {

    private AppService appService;

    private Label goldAmount;

    public GoldService() {

    }

    public GoldService(AppService appService,
                       Label goldAmount) {
        this.appService = appService;
        this.goldAmount = goldAmount;
    }

    public AppService getAppService() {
        return appService;
    }

    public GoldService setAppService(AppService appService) {
        this.appService = appService;
        return this;
    }

    public Label getGoldAmount() {
        return goldAmount;
    }

    public GoldService setGoldAmount(Label goldAmount) {
        this.goldAmount = goldAmount;
        return this;
    }
}
