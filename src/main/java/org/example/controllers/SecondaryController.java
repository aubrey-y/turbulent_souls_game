package org.example.controllers;

import java.io.IOException;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.example.App;

public class SecondaryController {

    @FXML
    private TextField nameID;
    private Button startButton;

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    /**
    @FXML
    private void switchToGameScreen() throws IOException {
        App.setRoot("gameScreen");
    }
     */

    @FXML
    void switchToGameScreen(ActionEvent event) {

    }


}