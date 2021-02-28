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

public class PrimaryController extends BaseController implements Initializable {

    @FXML
    private Button primaryButton;

    @FXML
    private Label startLabel;

    @FXML
    private Button closeButton;

    @FXML
    private Button soundToggle;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.initController();
    }

    @FXML
    private void closeButtonAction() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void switchToSecondary() throws IOException {
        this.appService.setRoot("secondary");
    }

    public AppService getAppService() {
        return appService;
    }

    public PrimaryController setAppService(AppService appService) {
        this.appService = appService;
        return this;
    }
}
