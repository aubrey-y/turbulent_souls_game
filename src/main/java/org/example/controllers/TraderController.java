package org.example.controllers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import org.example.services.AppService;
import org.example.services.DirectionService;
import org.example.services.HealthService;
import org.example.services.PlayerService;
import org.example.services.RoomDirectionService;
import org.example.services.SaveService;
import org.example.services.TraderService;
import org.example.util.TraderRoomUtility;

import java.nio.file.Paths;

public class TraderController extends GameScreenController {
    protected VBox traderVBox;

    protected Label traderPreviewTitle;

    protected Label traderPreviewStat;

    protected Label traderPreviewDescription;

    protected Rectangle traderPreviewBackground;

    protected ImageView traderPreviewImage;

    @FXML
    private Pane traderPane;

    protected ImageView trader;

    public TraderController() {

    }

    public TraderController(AppService appService,
                            PlayerService playerService,
                            DirectionService directionService,
                            RoomDirectionService roomDirectionService,
                            HealthService healthService,
                            SaveService saveService, Scene scene) {
        super(
                appService,
                playerService,
                directionService,
                roomDirectionService,
                healthService,
                saveService,
                scene
        );
    }

    protected void initializeTraderService(TraderService traderService, AppService appService) {
        this.traderVBox = new VBox();
        this.traderPane.getChildren().add(traderVBox);
        this.traderVBox.setVisible(false);
        this.traderPreviewBackground = new Rectangle();
        this.traderPane.getChildren().add(traderPreviewBackground);
        this.traderPreviewBackground.setVisible(false);
        this.traderPreviewDescription = new Label();
        this.traderPane.getChildren().add(traderPreviewDescription);
        this.traderPreviewDescription.setVisible(false);
        this.traderPreviewImage = new ImageView();
        this.traderPane.getChildren().add(traderPreviewImage);
        this.traderPreviewImage.setVisible(false);
        this.traderPreviewStat = new Label();
        this.traderPane.getChildren().add(traderPreviewStat);
        this.traderPreviewStat.setVisible(false);
        this.traderPreviewTitle = new Label();
        this.traderPane.getChildren().add(traderPreviewTitle);
        this.traderPreviewTitle.setVisible(false);
        this.traderPreviewBackground.setLayoutX(1400);
        this.traderPreviewBackground.setLayoutY(100);
        this.traderPreviewImage.setLayoutX(1400);
        this.traderPreviewImage.setLayoutY(150);
        this.traderPreviewTitle.setLayoutX(1410);
        this.traderPreviewTitle.setLayoutY(100);
        this.traderPreviewStat.setLayoutX(1410);
        this.traderPreviewStat.setLayoutY(593);
        this.traderPreviewDescription.setLayoutX(1410);
        this.traderPreviewDescription.setLayoutY(643);
        this.traderVBox.setLayoutX(50);
        this.traderVBox.setLayoutY(150);
        traderService
                .setAppService(appService)
                .setTraderVBox(this.traderVBox)
                .setTraderPreviewTitle(this.traderPreviewTitle)
                .setTraderPreviewStat(this.traderPreviewStat)
                .setTraderPreviewDescription(this.traderPreviewDescription)
                .setTraderPreviewBackground(this.traderPreviewBackground)
                .setTraderPreviewImage(this.traderPreviewImage)
                .setTraderBackground(this.inventoryBackground);
        this.trader = new ImageView(Paths.get(TraderRoomUtility.correctTraderPathUtility(
                appService.getActiveRoom().getRoomType())).toUri().toString());
        this.traderPane.getChildren().add(trader);
        this.trader.setTranslateX(913);
        this.trader.setTranslateY(500);
        this.trader.setFitHeight(160);
        this.trader.setFitWidth(160);
    }

}
