package org.example.controllers;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.example.App;

import java.net.URL;
import java.util.ResourceBundle;


public class GameScreenController implements Initializable {

    @FXML
    private javafx.scene.control.Button closeButton;

    @FXML
    private Label goldAmount;
    
    @FXML
    private void closeButtonAction() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.goldAmount.setText(String.valueOf(App.getPlayerState().getGoldAmount()));
    }
}





