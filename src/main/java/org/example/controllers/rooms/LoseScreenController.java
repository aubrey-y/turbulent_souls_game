package org.example.controllers.rooms;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.example.App;

import java.io.IOException;

public class LoseScreenController {

    @FXML
    private Button closeButton;

    @FXML
    private Button returnButton;

    @FXML
    private void closeButtonAction() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void returnButtonAction() throws IOException {
        App.setRoot(new FXMLLoader(App.class.getResource("primary.fxml")).load());
    }
}
