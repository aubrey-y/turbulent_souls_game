package org.example.controllers;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.example.services.AppService;

import java.net.URL;
import java.util.ResourceBundle;


public class GameScreenController implements Initializable {

    private AppService appService;

    @FXML
    private javafx.scene.control.Button closeButton;

    @FXML
    private Label goldAmount;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.appService = new AppService();
        this.goldAmount.setText(String.valueOf(this.appService.getPlayerState().getGoldAmount()));
    }
    
    @FXML
    private void closeButtonAction() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}





