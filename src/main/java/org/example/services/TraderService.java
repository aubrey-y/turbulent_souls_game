package org.example.services;

import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import org.example.dto.Item;
import org.example.dto.PlayerState;
import org.example.dto.Potion;
import org.example.dto.Weapon;
import org.example.util.CloneUtility;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static org.example.util.ResourcePathUtility.TOGGLE_BUTTON_STYLE_PATH;

public class TraderService {

    private AppService appService;

    private GoldService goldService;

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
            this.traderVBox.getChildren().clear();
            this.loadTraderElements();
        } else {
            this.traderVBox.getChildren().clear();
        }
        this.traderBackground.setVisible(!this.traderOpen);
        this.traderPreviewBackground.setVisible(!this.traderOpen);
        this.traderPreviewDescription.setVisible(!this.traderOpen);
        this.traderPreviewImage.setVisible(!this.traderOpen);
        this.traderPreviewStat.setVisible(!this.traderOpen);
        this.traderPreviewTitle.setVisible(!this.traderOpen);
        this.traderPreviewBackground.setVisible(!this.traderOpen);
        this.traderOpen = !this.traderOpen;
    }

    private void loadTraderElements() {
        traderItems.selectedToggleProperty().addListener((obsVal, oldVal, newVal) -> {
            if (newVal == null) {
                oldVal.setSelected(true);
            }
        });
        int index = 0;
        List<Item> sortedItems = new ArrayList<>(this.traderInventory.values());
        sortedItems.sort(Comparator.comparingInt(Item::getPrice));
        boolean selected = false;
        for (Item item : sortedItems) {
            ToggleButton toggleButton = new ToggleButton();
            toggleButton.setToggleGroup(this.traderItems);
            toggleButton.setFocusTraversable(false);
            toggleButton.getStylesheets()
                    .add(Paths.get(TOGGLE_BUTTON_STYLE_PATH).toUri().toString());
            toggleButton.setTextFill(Paint.valueOf("white"));
            toggleButton.setPrefWidth(1780);
            toggleButton.setPrefHeight(100);
            toggleButton.setFont(new Font("Pixeboy", 60));
            int finalI = index;
            toggleButton.setOnAction(actionEvent -> this.selectToggleButton(
                    item, finalI));
            toggleButton.setText(
                    String.format("%s | %s gold",
                            item.getName(), item.getPrice()));
            this.traderVBox.getChildren().add(toggleButton);
            index++;
            if (!selected) {
                toggleButton.setSelected(true);
                this.selectToggleButton(item, index);
                selected = true;
            }
            if (item instanceof Weapon && this.appService.getPlayerState()
                    .getWeaponInventory().containsKey(item.getImagePath())) {
                toggleButton.setDisable(true);
            }
        }
    }

    public void purchaseItem() {
        Item item = traderInventory.get(selectedItem.getImagePath());
        PlayerState playerState = this.appService.getPlayerState();
        Map<String, Item> weaponInventory = playerState.getWeaponInventory();
        Map<String, Item> generalInventory = playerState.getGeneralInventory();
        if (!this.goldService.playerCanAffordAmount(item.getPrice())) {
            return;
        } else {
            playerState = this.goldService.adjustGoldAmount(-1 * item.getPrice());
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
            } else {
                Item playerItem = generalInventory.get(item.getImagePath());
                playerItem.setQuantity(playerItem.getQuantity() + 1);
                generalInventory.put(item.getImagePath(), playerItem);
                playerState.setGeneralInventory(generalInventory);
            }
        }
        this.appService.setPlayerState(playerState);
        if (item instanceof Weapon) {
            this.traderVBox.getChildren().clear();
            this.loadTraderElements();
        }
    }

    private void selectToggleButton(Item item, int index) {
        selectedItem = item;

        Item icon = traderInventory.get(item);

        this.traderPreviewTitle.setText(item.getName());
        this.traderPreviewImage.setImage(
                new Image(Paths.get(item.getImagePath()).toUri().toString()));
        if (item instanceof Weapon) {
            Weapon weapon = (Weapon) item;
            this.traderPreviewStat.setText("Base ATK: " + ((Weapon) item).getAttack());
        } else if (item instanceof Potion) {
            Potion potion = (Potion) item;
            this.traderPreviewStat.setText(potion.getStatLabel() + " " + potion.getStatValue());
            this.traderPreviewDescription.setText(potion.getDescription());
        } else {
            throw new RuntimeException();
        }
        this.traderPreviewDescription.setText(item.getDescription());
    }

    public AppService getAppService() {
        return appService;
    }

    public TraderService setAppService(AppService appService) {
        this.appService = appService;
        return this;
    }

    public GoldService getGoldService() {
        return goldService;
    }

    public TraderService setGoldService(GoldService goldService) {
        this.goldService = goldService;
        return this;
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

    public Map<String, Item> getTraderInventory() {
        return traderInventory;
    }

    public TraderService setTraderInventory(Map<String, Item> traderInventory) {
        this.traderInventory = traderInventory;
        return this;
    }


}
