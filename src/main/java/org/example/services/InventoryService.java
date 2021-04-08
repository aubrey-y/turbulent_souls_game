package org.example.services;

import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import org.example.dto.BasicHealthPotion;
import org.example.dto.BasicMagic;
import org.example.dto.BasicStaff;
import org.example.dto.BasicSword;
import org.example.dto.Item;
import org.example.dto.Potion;
import org.example.dto.Weapon;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static org.example.util.ResourcePathUtility.TOGGLE_BUTTON_STYLE_PATH;

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
    private ToggleGroup inventoryToggleGroup = new ToggleGroup();
    private String recentlySelected;

    public InventoryService(AppService appService) {
        this.appService = appService;
    }

    public void toggleInventoryOpen() {
        if(!this.inventoryOpen) {
            Map<String, Weapon> weaponInventory = this.appService.getPlayerState()
                    .getWeaponInventory();
            Map<String, Item> generalInventory = this.appService.getPlayerState()
                    .getGeneralInventory();
            boolean selected = false;
            for(Weapon weapon : weaponInventory.values()) {
                ToggleButton toggleButton = new ToggleButton();
                toggleButton.setToggleGroup(this.inventoryToggleGroup);
                toggleButton.getStylesheets()
                        .add(Paths.get(TOGGLE_BUTTON_STYLE_PATH).toUri().toString());
                toggleButton.setGraphic(
                        new ImageView(Paths.get(weapon.getImagePath()).toUri().toString()));
                toggleButton.setOnAction(actionEvent -> {
                    this.selectToggleButton(weapon.getImagePath());
                });
                this.inventoryRow1.getChildren().add(toggleButton);
                if (!selected) {
                    toggleButton.setSelected(true);
                    this.selectToggleButton(weapon.getImagePath());
                    selected = true;
                }
            }
            for (Item item : generalInventory.values()) {
                ToggleButton toggleButton = new ToggleButton();
                toggleButton.setToggleGroup(this.inventoryToggleGroup);
                toggleButton.getStylesheets()
                        .add(Paths.get(TOGGLE_BUTTON_STYLE_PATH).toUri().toString());
                toggleButton.setGraphic(
                        new ImageView(Paths.get(item.getImagePath()).toUri().toString()));
                toggleButton.setOnAction(actionEvent -> {
                    this.selectToggleButton(item.getImagePath());
                });
                this.inventoryRow2.getChildren().add(toggleButton);
            }
        } else {
            this.inventoryRow1.getChildren().clear();
            this.inventoryRow2.getChildren().clear();
            this.inventoryRow3.getChildren().clear();
            this.inventoryRow4.getChildren().clear();
            this.inventoryRow5.getChildren().clear();
        }
        this.inventoryBackground.setVisible(!this.inventoryOpen);
        this.inventoryPreviewBackground.setVisible(!this.inventoryOpen);
        this.inventoryPreviewImage.setVisible(!this.inventoryOpen);
        this.inventoryPreviewTitle.setVisible(!this.inventoryOpen);
        this.inventoryPreviewStat.setVisible(!this.inventoryOpen);
        this.inventoryPreviewDescription.setVisible(!this.inventoryOpen);
        this.inventoryOpen = !inventoryOpen;
    }

    private void selectToggleButton(String pathId) {
        Map<String, Weapon> weaponInventory = this.appService.getPlayerState()
                .getWeaponInventory();
        Map<String, Item> generalInventory = this.appService.getPlayerState()
                .getGeneralInventory();
        recentlySelected = pathId;

        Weapon weapon = weaponInventory.get(pathId);
        if (weapon != null) {
            this.inventoryPreviewTitle.setText(weapon.getName());
            this.inventoryPreviewImage.setImage(
                    new Image(Paths.get(weapon.getImagePath()).toUri().toString()));
            this.inventoryPreviewStat.setText("Base ATK: " + weapon.getAttack());
            this.inventoryPreviewDescription.setText(weapon.getDescription());
        } else {
            Item item = generalInventory.get(pathId);
            this.inventoryPreviewTitle.setText(item.getName());
            this.inventoryPreviewImage.setImage(
                    new Image(Paths.get(item.getImagePath()).toUri().toString()));
            if (item instanceof Potion) {
                Potion potion = (Potion) item;
                this.inventoryPreviewStat.setText(potion.getStatLabel() + " " + potion.getStatValue());
                this.inventoryPreviewDescription.setText(potion.getDescription());
            }
        }

    }

    public void useItem(ConsumableService consumableService) {
        Map<String, Weapon> weaponInventory = this.appService.getPlayerState()
                .getWeaponInventory();
        Map<String, Item> generalInventory = this.appService.getPlayerState()
                .getGeneralInventory();
        Weapon weapon = weaponInventory.get(recentlySelected);
        if (weapon != null) {
            this.appService.getPlayerState().setActiveWeapon(weapon);
        } else {
            Item item = generalInventory.get(recentlySelected);
            if (item != null) {
                if (item instanceof Potion) {
                    Potion potion = (Potion) item;
                    consumableService.consumePotion(potion);
                }
                Integer newQuantity = item.getQuantity();
                if (newQuantity > 0) {
                    newQuantity -= 1;
                }
                item.setQuantity(newQuantity);
                if (item.getQuantity() == 0) {
                    //Logic here to remove icon from inventory
                }
            }
        }
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

    public boolean getInventoryOpen() {
        return inventoryOpen;
    }
}
