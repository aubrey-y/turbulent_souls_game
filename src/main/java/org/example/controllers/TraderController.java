package org.example.controllers;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import org.example.services.AppService;
import org.example.services.TraderService;

public class TraderController {
    protected VBox traderVBox;

    protected Label traderPreviewTitle;

    protected Label traderPreviewStat;

    protected Label traderPreviewDescription;

    protected Rectangle traderPreviewBackground;

    protected ImageView traderPreviewImage;

    protected ImageView traderBackground;

    protected void initializeTraderService(TraderService traderService, AppService appService) {
        this.traderVBox = new VBox();
        this.traderVBox.setVisible(false);
        this.traderBackground = new ImageView("");
        this.traderBackground.setVisible(false);
        this.traderPreviewBackground = new Rectangle();
        this.traderPreviewBackground.setVisible(false);
        this.traderPreviewDescription = new Label();
        this.traderPreviewDescription.setVisible(false);
        this.traderPreviewImage = new ImageView();
        this.traderPreviewImage.setVisible(false);
        this.traderPreviewStat = new Label();
        this.traderPreviewStat.setVisible(false);
        this.traderPreviewTitle = new Label();
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
                .setTraderBackground(this.traderBackground);
    }

}
