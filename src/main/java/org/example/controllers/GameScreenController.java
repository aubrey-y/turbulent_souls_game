package org.example.controllers;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class GameScreenController {

    @FXML
    private javafx.scene.control.Button closeButton;

    @FXML
    private Label gold;

    @FXML
    public void displayGold(int amountOfGold) {
        gold.setText("Gold: " + amountOfGold);
    }
    
    @FXML
    private void closeButtonAction(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

}





