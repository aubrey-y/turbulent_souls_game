package org.example;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class PrimaryController {

    @FXML
    private Button primaryButton;

    @FXML
    private Label startLabel;

    @FXML
    private void switchToSecondary() throws IOException, InterruptedException {
        App.setRoot("secondary");
    }

}
