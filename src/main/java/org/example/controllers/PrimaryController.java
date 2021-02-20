package org.example.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.example.App;

public class PrimaryController {

    @FXML
    private Button primaryButton;

    @FXML
    private Label startLabel;

    @FXML
    private Button closeButton;

    @FXML
    private Button soundToggle;

    @FXML
    private void closeButtonAction(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void switchToSecondary() throws IOException, InterruptedException {
        App.setRoot("secondary");
    }

    @FXML
    private void toggleSound() {
        App.toggleSound();
    }

}
