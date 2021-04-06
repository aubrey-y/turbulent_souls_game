package org.example.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.CheckBox;
import org.example.App;
import org.example.services.AppService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.util.ResourceBundle;

public class OptionsController extends ErrorBaseController {

    private Scene scene;

    private AppService appService;

    @FXML
    private Rectangle warningBackground;

    @FXML
    private CheckBox devMode;

    @FXML
    private TextField passwordEntry;

    public OptionsController(Scene scene,
                             AppService appService) {
        this.scene = scene;
        this.appService = appService;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.initController();
        if (this.appService.getDevMode()) {
            this.devMode.setSelected(true);
        }
    }

    @FXML
    private void verifyCredentials() {
        if (this.appService.getDevMode()) {
            this.appService.setDevMode(false);
            return;
        }
        this.devMode.setOpacity(0.2);
        this.warningBackground.setVisible(true);
        this.passwordEntry.setVisible(true);
        this.scene.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                checkPassword();
            }
        });
    }

    private void acceptCredentials() {
        this.devMode.setOpacity(1.0);
        this.warningBackground.setVisible(false);
        this.passwordEntry.setVisible(false);
        this.scene.setOnKeyReleased(e -> { });
        this.appService.setDevMode(true);
        this.hideErrorMessage();
    }

    private void checkPassword() {
        if (passwordEntry.getText().equals(System.getenv("DEVMODE_PW"))) {
            this.acceptCredentials();
        } else {
            this.setErrorMessage("Invalid password, this has been reported.");
        }
    }

    @FXML
    private void switchToPrimary() {
        this.scene.setOnKeyReleased(e -> { });
        FXMLLoader loader = new FXMLLoader(App.class.getResource("primary.fxml"));
        this.appService.setRoot(loader);
    }
}
