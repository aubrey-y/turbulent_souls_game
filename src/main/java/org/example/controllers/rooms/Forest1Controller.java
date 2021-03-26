package org.example.controllers.rooms;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import org.example.controllers.GameScreenController;
import org.example.dto.Monster;
import org.example.enums.MonsterType;
import org.example.services.AppService;
import org.example.services.DirectionService;
import org.example.services.HealthService;
import org.example.services.MonsterService;
import org.example.services.PlayerService;
import org.example.services.RoomDirectionService;

import java.net.URL;
import java.util.ResourceBundle;

import static org.example.enums.MonsterType.SLIME;

public class Forest1Controller extends GameScreenController implements Initializable {

    private MonsterService monsterService;

    @FXML
    private ImageView slime1;
    private final int slime1Key = 1;
    private final int slime1HealthCapacity = 10;

    @FXML
    private ProgressBar slime1HealthBar;

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
        this.monsterService = new MonsterService();
        this.initGameScreenController(Forest1Controller.class, this.monsterService);
        slime1.setVisible(true);
        slime1.setTranslateX(1500);
        slime1.setTranslateY(400);
        slime1HealthBar.setVisible(true);
        slime1HealthBar.setTranslateX(1570);
        slime1HealthBar.setTranslateY(400);
        this.monsterService.addMonster(
                this.slime1Key,
                new Monster()
                        .setHealth(this.slime1HealthCapacity)
                        .setHealthCapacity(this.slime1HealthCapacity)
                        .setMonsterType(SLIME)
                        .setImageView(this.slime1)
                        .setHealthBar(this.slime1HealthBar));
    }
}
