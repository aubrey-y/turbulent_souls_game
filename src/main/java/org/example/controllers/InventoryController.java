package org.example.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;

public abstract class InventoryController {

    @FXML
    protected ImageView inventoryBackground;

    @FXML
    protected Rectangle inventoryPreviewBackground;

    @FXML
    protected ImageView inventoryPreviewImage;

    @FXML
    protected Label inventoryPreviewTitle;

    @FXML
    protected Label inventoryPreviewStat;

    @FXML
    protected Label inventoryPreviewDescription;

    @FXML
    protected HBox inventoryRow1;

    @FXML
    protected HBox inventoryRow2;

    @FXML
    protected HBox inventoryRow3;

    @FXML
    protected HBox inventoryRow4;

    @FXML
    protected HBox inventoryRow5;
}
