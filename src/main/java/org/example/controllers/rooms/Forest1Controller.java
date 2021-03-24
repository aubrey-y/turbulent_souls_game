package org.example.controllers.rooms;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import org.example.controllers.GameScreenController;
import org.example.services.AppService;
import org.example.services.DirectionService;
import org.example.services.HealthService;
import org.example.services.PlayerService;
import org.example.services.RoomDirectionService;

import java.net.URL;
import java.util.ResourceBundle;

public class Forest1Controller extends GameScreenController implements Initializable {

    @FXML
    private ImageView slime_1;

    public Forest1Controller(AppService appService,
                             PlayerService playerService,
                             DirectionService directionService,
                             RoomDirectionService roomDirectionService,
                             HealthService healthService, Scene scene) {
        super(
                appService,
                playerService,
                directionService,
                roomDirectionService,
                healthService,
                scene
        );
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.initGameScreenController(Forest1Controller.class);
    }
}
