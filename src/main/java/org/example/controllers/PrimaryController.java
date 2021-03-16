package org.example.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.example.App;
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
    private void switchToSecondary() {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("secondary.fxml"));
        loader.setControllerFactory(SecondaryController -> new SecondaryController(
                this.appService.getScene()));
        this.appService.setRoot(loader);
    }

    @FXML
    private void switchToOptions() {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("options.fxml"));
        loader.setControllerFactory(OptionsController -> new OptionsController(
                this.appService.getScene(),
                this.appService
        ));
        this.appService.setRoot(loader);
    }

    public AppService getAppService() {
        return appService;
    }

    public PrimaryController setAppService(AppService appService) {
        this.appService = appService;
        return this;
    }
}
