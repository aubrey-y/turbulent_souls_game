package org.example.controllers;


import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.example.services.AppService;
import org.example.services.PlayerService;

import java.net.URL;
import java.util.ResourceBundle;


public class GameScreenController implements Initializable {

    private AppService appService;

    private PlayerService playerService;

    @FXML
    private javafx.scene.control.Button closeButton;

    @FXML
    private Label goldAmount;

    @FXML
    private ImageView player;

    private final BooleanProperty wPressed = new SimpleBooleanProperty(false);
    private final BooleanProperty aPressed = new SimpleBooleanProperty(false);
    private final BooleanProperty sPressed = new SimpleBooleanProperty(false);
    private final BooleanProperty dPressed = new SimpleBooleanProperty(false);
    private final BooleanBinding waPressed = this.wPressed.and(this.aPressed);
    private final BooleanBinding wdPressed = this.wPressed.and(this.dPressed);
    private final BooleanBinding saPressed = this.sPressed.and(this.aPressed);
    private final BooleanBinding sdPressed = this.sPressed.and(this.dPressed);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.appService = new AppService();
        this.goldAmount.setText(String.valueOf(this.appService.getPlayerState().getGoldAmount()));
        this.playerService = new PlayerService(player);
        this.playerService.moveX(400);
        this.playerService.moveY(540);
        this.playerService.setVisible(true);
        this.appService.getScene().setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case W:
                    this.wPressed.set(true);
                    break;
                case A:
                    this.aPressed.set(true);
                    break;
                case S:
                    this.sPressed.set(true);
                    break;
                case D:
                    this.dPressed.set(true);
                    break;
            }

            if(this.waPressed.get()) {
                this.playerService.moveUp();
                this.playerService.moveLeft();
            } else if(this.wdPressed.get()) {
                this.playerService.moveUp();
                this.playerService.moveRight();
            } else if(this.saPressed.get()) {
                this.playerService.moveDown();
                this.playerService.moveLeft();
            } else if(this.sdPressed.get()) {
                this.playerService.moveDown();
                this.playerService.moveRight();
            } else {
                if(this.wPressed.get()) {
                    this.playerService.moveUp();
                } else if(this.aPressed.get()) {
                    this.playerService.moveLeft();
                } else if(this.sPressed.get()) {
                    this.playerService.moveDown();
                } else if(this.dPressed.get()){
                    this.playerService.moveRight();
                }
            }
        });
        this.appService.getScene().setOnKeyReleased(e -> {
            switch (e.getCode()) {
                case W:
                    this.wPressed.set(false);
                    break;
                case A:
                    this.aPressed.set(false);
                    break;
                case S:
                    this.sPressed.set(false);
                    break;
                case D:
                    this.dPressed.set(false);
                    break;
            }
        });
    }
    
    @FXML
    private void closeButtonAction() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}





