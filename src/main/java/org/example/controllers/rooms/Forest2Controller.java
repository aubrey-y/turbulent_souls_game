package org.example.controllers.rooms;

import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import org.example.controllers.GameScreenController;
import org.example.services.AppService;
import org.example.services.DirectionService;
import org.example.services.HealthService;
import org.example.services.MonsterService;
import org.example.services.PlayerService;
import org.example.services.RoomDirectionService;

import java.net.URL;
import java.util.ResourceBundle;

public class Forest2Controller extends GameScreenController implements Initializable {

    private MonsterService monsterService;

    private Timeline guineapig1AttackSchedule;
    private Timeline resetPlayerSchedule;

    @FXML
    private ImageView guineapig1;
    private final String guineapig1Key = "forest2guineapig1";
    private final int guineapig1HealthCapacity = 10;

    public Forest2Controller(AppService appService,
                             PlayerService playerService,
                             DirectionService directionService,
                             RoomDirectionService roomDirectionService,
                             HealthService healthService, Scene scene) {
        super(
                appService,
                playerService,
                directionService,
                roomDirectionService,
                healthService, scene
        );
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.monsterService = new MonsterService();
        this.initGameScreenController(this.monsterService);
    }
}
