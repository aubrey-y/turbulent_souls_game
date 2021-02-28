package org.example.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.example.services.AppService;

public class PrimaryController implements Initializable {

    @FXML
    private Button primaryButton;

    @FXML
    private Label startLabel;

    @FXML
    private Button closeButton;

    @FXML
    private Button soundToggle;

    private AppService appService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.appService = new AppService();
    }

    @FXML
    private void closeButtonAction() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void switchToSecondary() throws IOException, InterruptedException {
        this.appService.setRoot("secondary");
    }

    @FXML
    private void toggleSound() {
        this.appService.toggleSound();
    }

    public AppService getAppService() {
        return appService;
    }

    public PrimaryController setAppService(AppService appService) {
        this.appService = appService;
        return this;
    }
}
