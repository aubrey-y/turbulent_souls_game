package org.example.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public abstract class ErrorBaseController extends BaseController {

    @FXML
    protected TextField usernameField;

    @FXML
    protected Rectangle errorBox;

    @FXML
    protected Label errorText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.initController();
    }

    @FXML
    protected void setErrorMessage(String message) {
        this.errorBox.setOpacity(1.0);
        this.errorText.setText(message);
        this.errorText.setOpacity(1.0);
    }

    @FXML
    protected void hideErrorMessage() {
        this.errorBox.setOpacity(0.0);
        this.errorText.setOpacity(0.0);
    }

    public ErrorBaseController setUsernameField(TextField usernameField) {
        this.usernameField = usernameField;
        return this;
    }

    public ErrorBaseController setErrorBox(Rectangle errorBox) {
        this.errorBox = errorBox;
        return this;
    }

    public ErrorBaseController setErrorText(Label errorText) {
        this.errorText = errorText;
        return this;
    }

    public TextField getUsernameField() {
        return usernameField;
    }

    public Rectangle getErrorBox() {
        return errorBox;
    }

    public Label getErrorText() {
        return errorText;
    }
}
