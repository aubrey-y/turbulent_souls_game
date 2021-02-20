package org.example;


import javafx.fxml.FXML;
import javafx.stage.Stage;


public class GameScreenController {

    @FXML
    private javafx.scene.control.Button closeButton;

    @FXML
    private void closeButtonAction(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

}





