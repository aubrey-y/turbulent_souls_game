package org.example.services;

import javafx.scene.control.Label;
import org.example.dto.PlayerState;

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

    public boolean playerCanAffordAmount(int price) {
        return this.appService.getPlayerState().getGoldAmount() >= price;
    }

    public PlayerState adjustGoldAmount(int amount) {
        PlayerState playerState = this.appService.getPlayerState();
        playerState.setGoldAmount(playerState.getGoldAmount() + amount);
        this.appService.setPlayerState(playerState);
        this.goldAmount.setText(String.valueOf(playerState.getGoldAmount()));
        return playerState;
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
