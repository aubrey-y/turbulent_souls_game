package org.example.services;

import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import org.example.dto.Item;
import org.example.dto.PlayerState;
import org.example.dto.Weapon;
import org.example.util.CloneUtility;

import java.nio.file.Paths;
import java.util.Map;

import static org.example.util.ResourcePathUtility.FONT_STYLE_PATH;
import static org.example.util.ResourcePathUtility.TOGGLE_BUTTON_STYLE_PATH;

public class TraderService {

    private AppService appService;

    private Map<String, Item> traderInventory;

    private ToggleGroup traderItems = new ToggleGroup();

    private boolean traderOpen;

    private static Item selectedItem;

    private static int selectedSaveIndex;

    private VBox traderVBox;

    private Label traderPreviewTitle;

    private Label traderPreviewStat;

    private Label traderPreviewDescription;

    private Rectangle traderPreviewBackground;

    private ImageView traderPreviewImage;

    private ImageView traderBackground;

    public TraderService() {

    }

    public TraderService(Map<String, Item> traderInventory) {
        this.traderInventory = traderInventory;
    }

    public TraderService(AppService appService) {
        this.appService = appService;
    }

    public void toggleTraderOpen() {
        if (!this.traderOpen) {
            this.loadTraderElements();
        } else {
            this.traderVBox.getChildren().clear();
        }
        this.traderVBox.setVisible(!this.traderOpen);
        this.traderPreviewBackground.setVisible(!this.traderOpen);
        this.traderPreviewDescription.setVisible(!this.traderOpen);
        this.traderPreviewImage.setVisible(!this.traderOpen);
        this.traderPreviewStat.setVisible(!this.traderOpen);
        this.traderPreviewTitle.setVisible(!this.traderOpen);
        this.traderPreviewBackground.setVisible(!this.traderOpen);
        this.traderOpen = !this.traderOpen;
    }

    private void loadTraderElements() {
        int index = 0;
        for (String key : traderInventory.keySet()) {
            Item item = traderInventory.get(key);
            ToggleButton toggleButton = new ToggleButton();
            toggleButton.setToggleGroup(this.traderItems);
            toggleButton.setFocusTraversable(false);
            toggleButton.getStylesheets()
                    .add(Paths.get(TOGGLE_BUTTON_STYLE_PATH).toUri().toString());
            toggleButton.getStylesheets()
                    .add(Paths.get(FONT_STYLE_PATH).toUri().toString());
            toggleButton.setTextFill(Paint.valueOf("white"));
            toggleButton.setPrefWidth(1780);
            toggleButton.setPrefHeight(100);
            toggleButton.setFont(new Font(60));
            int finalI = index;
            toggleButton.setOnAction(actionEvent -> this.selectToggleButton(
                    item, finalI));
            toggleButton.setText(
                    String.format("%s | %s gold",
                            item.getName(), item.getPrice()));
            this.traderVBox.getChildren().add(toggleButton);
        }
    }

    public void purchaseItem() {
        Item item = traderInventory.get(selectedItem);
        PlayerState playerState = this.appService.getPlayerState();
        Map<String, Item> weaponInventory = playerState.getWeaponInventory();
        Map<String, Item> generalInventory = playerState.getGeneralInventory();
        if (playerState.getGoldAmount() < item.getPrice()) {
            return;
        } else {
            playerState.setGoldAmount(playerState.getGoldAmount() - item.getPrice());
        }
        if (item instanceof Weapon) {
            if (!weaponInventory.containsKey(item.getImagePath())) {
                weaponInventory.put(item.getImagePath(), (Item) CloneUtility.deepCopy(item));
            } else {
                Item weapon = weaponInventory.get(item.getImagePath());
                weapon.setQuantity(weapon.getQuantity() + 1);
                weaponInventory.put(item.getImagePath(), weapon);
            }
            playerState.setWeaponInventory(weaponInventory);
        } else {
            if (!generalInventory.containsKey(item.getImagePath())) {
                generalInventory.put(item.getImagePath(), (Item) CloneUtility.deepCopy(item));
            }
            Item playerItem = generalInventory.get(item.getImagePath());
            playerItem.setQuantity(item.getQuantity() + 1);
            generalInventory.put(item.getImagePath(), playerItem);
            playerState.setGeneralInventory(weaponInventory);
        }
    }

    private void selectToggleButton(Item item, int index) {
        selectedItem = item;
        selectedSaveIndex = index;

    }

    public boolean isTraderOpen() {
        return traderOpen;
    }

    public TraderService setTraderOpen(boolean traderOpen) {
        this.traderOpen = traderOpen;
        return this;
    }

    public VBox getTraderVBox() {
        return traderVBox;
    }

    public TraderService setTraderVBox(VBox traderVBox) {
        this.traderVBox = traderVBox;
        return this;
    }

    public Label getTraderPreviewTitle() {
        return traderPreviewTitle;
    }

    public TraderService setTraderPreviewTitle(Label traderPreviewTitle) {
        this.traderPreviewTitle = traderPreviewTitle;
        return this;
    }

    public Label getTraderPreviewStat() {
        return traderPreviewStat;
    }

    public TraderService setTraderPreviewStat(Label traderPreviewStat) {
        this.traderPreviewStat = traderPreviewStat;
        return this;
    }

    public Label getTraderPreviewDescription() {
        return traderPreviewDescription;
    }

    public TraderService setTraderPreviewDescription(Label traderPreviewDescription) {
        this.traderPreviewDescription = traderPreviewDescription;
        return this;
    }

    public Rectangle getTraderPreviewBackground() {
        return traderPreviewBackground;
    }

    public TraderService setTraderPreviewBackground(Rectangle traderPreviewBackground) {
        this.traderPreviewBackground = traderPreviewBackground;
        return this;
    }

    public ImageView getTraderPreviewImage() {
        return traderPreviewImage;
    }

    public TraderService setTraderPreviewImage(ImageView traderPreviewImage) {
        this.traderPreviewImage = traderPreviewImage;
        return this;
    }

    public ImageView getTraderBackground() {
        return traderBackground;
    }

    public TraderService setTraderBackground(ImageView traderBackground) {
        this.traderBackground = traderBackground;
        return this;
    }

    public AppService getAppService() {
        return appService;
    }

    public TraderService setAppService(AppService appService) {
        this.appService = appService;
        return this;
    }

    public Map<String, Item> getTraderInventory() {
        return traderInventory;
    }

    public TraderService setTraderInventory(Map<String, Item> traderInventory) {
        this.traderInventory = traderInventory;
        return this;
    }


}
