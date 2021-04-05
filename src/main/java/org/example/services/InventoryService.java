package org.example.services;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;

public class InventoryService {

    private ImageView inventoryBackground;
    private Rectangle inventoryPreviewBackground;
    private ImageView inventoryPreviewImage;
    private Label inventoryPreviewTitle;
    private Label inventoryPreviewStat;
    private Label inventoryPreviewDescription;
    private HBox inventoryRow1;
    private HBox inventoryRow2;
    private HBox inventoryRow3;
    private HBox inventoryRow4;
    private HBox inventoryRow5;

    private boolean inventoryOpen;

    private AppService appService;

    public InventoryService(AppService appService) {
        this.appService = appService;
    }

    public void toggleInventoryOpen() {
        this.inventoryBackground.setVisible(!this.inventoryOpen);
        this.inventoryPreviewBackground.setVisible(!this.inventoryOpen);
        this.inventoryPreviewImage.setVisible(!this.inventoryOpen);
        this.inventoryPreviewTitle.setVisible(!this.inventoryOpen);
        this.inventoryPreviewStat.setVisible(!this.inventoryOpen);
        this.inventoryPreviewDescription.setVisible(!this.inventoryOpen);
        this.inventoryOpen = !inventoryOpen;
    }

    public ImageView getInventoryBackground() {
        return inventoryBackground;
    }

    public InventoryService setInventoryBackground(ImageView inventoryBackground) {
        this.inventoryBackground = inventoryBackground;
        return this;
    }

    public Rectangle getInventoryPreviewBackground() {
        return inventoryPreviewBackground;
    }

    public InventoryService setInventoryPreviewBackground(Rectangle inventoryPreviewBackground) {
        this.inventoryPreviewBackground = inventoryPreviewBackground;
        return this;
    }

    public ImageView getInventoryPreviewImage() {
        return inventoryPreviewImage;
    }

    public InventoryService setInventoryPreviewImage(ImageView inventoryPreviewImage) {
        this.inventoryPreviewImage = inventoryPreviewImage;
        return this;
    }

    public Label getInventoryPreviewTitle() {
        return inventoryPreviewTitle;
    }

    public InventoryService setInventoryPreviewTitle(Label inventoryPreviewTitle) {
        this.inventoryPreviewTitle = inventoryPreviewTitle;
        return this;
    }

    public Label getInventoryPreviewStat() {
        return inventoryPreviewStat;
    }

    public InventoryService setInventoryPreviewStat(Label inventoryPreviewStat) {
        this.inventoryPreviewStat = inventoryPreviewStat;
        return this;
    }

    public Label getInventoryPreviewDescription() {
        return inventoryPreviewDescription;
    }

    public InventoryService setInventoryPreviewDescription(Label inventoryPreviewDescription) {
        this.inventoryPreviewDescription = inventoryPreviewDescription;
        return this;
    }

    public HBox getInventoryRow1() {
        return inventoryRow1;
    }

    public InventoryService setInventoryRow1(HBox inventoryRow1) {
        this.inventoryRow1 = inventoryRow1;
        return this;
    }

    public HBox getInventoryRow2() {
        return inventoryRow2;
    }

    public InventoryService setInventoryRow2(HBox inventoryRow2) {
        this.inventoryRow2 = inventoryRow2;
        return this;
    }

    public HBox getInventoryRow3() {
        return inventoryRow3;
    }

    public InventoryService setInventoryRow3(HBox inventoryRow3) {
        this.inventoryRow3 = inventoryRow3;
        return this;
    }

    public HBox getInventoryRow4() {
        return inventoryRow4;
    }

    public InventoryService setInventoryRow4(HBox inventoryRow4) {
        this.inventoryRow4 = inventoryRow4;
        return this;
    }

    public HBox getInventoryRow5() {
        return inventoryRow5;
    }

    public InventoryService setInventoryRow5(HBox inventoryRow5) {
        this.inventoryRow5 = inventoryRow5;
        return this;
    }

    public AppService getAppService() {
        return appService;
    }

    public InventoryService setAppService(AppService appService) {
        this.appService = appService;
        return this;
    }
}
