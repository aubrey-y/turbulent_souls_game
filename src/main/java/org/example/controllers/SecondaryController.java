package org.example.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import org.example.App;
import org.example.controllers.rooms.Forest1Controller;
import org.example.dto.Coordinate;
import org.example.dto.PlayerState;
import org.example.dto.Room;
import org.example.enums.Archetype;
import org.example.enums.Difficulty;
import org.example.exceptions.InvalidArchetypeException;
import org.example.exceptions.InvalidDifficultyException;
import org.example.exceptions.InvalidNameException;
import org.example.exceptions.PlayerCreationException;
import org.example.services.AppService;
import org.example.services.DirectionService;
import org.example.services.HealthService;
import org.example.services.PlayerService;
import org.example.services.RoomDirectionService;
import org.example.services.SaveService;

import java.net.URL;
import java.util.ResourceBundle;

import static org.example.enums.RoomType.FOREST1;
import static org.example.exceptions.ExceptionMessages.INVALID_ARCHETYPE_EXCEPTION_MESSAGE;
import static org.example.exceptions.ExceptionMessages.INVALID_DIFFICULTY_EXCEPTION_MESSAGE;
import static org.example.exceptions.ExceptionMessages.INVALID_NAME_EXCEPTION_MESSAGE;
import static org.example.exceptions.ExceptionMessages.UNKNOWN_EXCEPTION_MESSAGE;

public class SecondaryController extends ErrorBaseController {

    private SaveService saveService;

    private Scene scene;

    private String username;

    private Difficulty difficulty;

    private Archetype archetype;

    public static final Coordinate SPAWN_COORDINATES = new Coordinate().setX(400).setY(540);

    @FXML
    private ToggleGroup toggleGroup1;

    @FXML
    private ToggleGroup toggleGroup2;

    @FXML
    private ToggleButton easyButton;

    @FXML
    private ToggleButton mediumButton;

    @FXML
    private ToggleButton hardButton;

    @FXML
    private ToggleButton mageButton;

    @FXML
    private ToggleButton warriorButton;

    @FXML
    private ToggleButton wizardButton;

    public SecondaryController(SaveService saveService,
                               Scene scene) {
        this.saveService = saveService;
        this.scene = scene;
    }

    public static final Room STARTING_ROOM = new Room(FOREST1)
            .setDown(new Room())
            .setRight(new Room())
            .setUp(new Room())
            .setLeft(new Room())
            .setId(0)
            .setControllerClass(Forest1Controller.class)
            .setRoot("gameScreen.fxml");

    @FXML
    private void switchToPrimary() {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("primary.fxml"));
        this.appService.setRoot(loader);
    }

    @FXML
    public void switchToGameScreen(ActionEvent event) {
        this.hideErrorMessage();
        try {
            validatePlayerName();
            validateDifficulty();
            validateArchetype();
            PlayerState playerState = new PlayerState(
                    this.username,
                    this.archetype,
                    this.difficulty,
                    SPAWN_COORDINATES)
                    .setEmail(this.appService.getPlayerState().getEmail());
            if (this.appService.getDevMode()) {
                playerState.setGoldAmount(1000000);
            }
            this.appService.setPlayerState(playerState);
            this.appService.setActiveRoom(STARTING_ROOM);
            FXMLLoader loader = new FXMLLoader(App.class.getResource("gameScreen.fxml"));
            DirectionService directionService = new DirectionService();
            RoomDirectionService roomDirectionService = new RoomDirectionService(directionService);
            HealthService healthService = new HealthService(this.appService);


            loader.setControllerFactory(GameScreenController -> new Forest1Controller(
                    this.appService,
                    new PlayerService(
                            this.appService,
                            roomDirectionService,
                            healthService,
                            this.saveService),
                    directionService,
                    roomDirectionService,
                    healthService,
                    this.saveService,
                    this.scene));
            this.appService.setRoot(loader);
        } catch (InvalidNameException e) {
            this.setErrorMessage(INVALID_NAME_EXCEPTION_MESSAGE);
        } catch (InvalidDifficultyException e) {
            this.setErrorMessage(INVALID_DIFFICULTY_EXCEPTION_MESSAGE);
        } catch (InvalidArchetypeException e) {
            this.setErrorMessage(INVALID_ARCHETYPE_EXCEPTION_MESSAGE);
        } catch (PlayerCreationException e) {
            this.setErrorMessage(UNKNOWN_EXCEPTION_MESSAGE);
        }
    }

    private void validatePlayerName() throws InvalidNameException {
        String username = this.usernameField.getText();
        if (username.isEmpty() || username.trim().equals("")) {
            throw new InvalidNameException("");
        } else {
            this.username = username;
        }
    }

    //No else statement because selection is already tracked
    private void validateDifficulty() throws InvalidDifficultyException {
        if (this.difficulty == null) {
            throw new InvalidDifficultyException("");
        }
    }

    private void validateArchetype() throws InvalidArchetypeException {
        if (this.archetype == null) {
            throw new InvalidArchetypeException("");
        }
    }

    @FXML
    private void selectEasyDifficulty() {
        selectionDifficultyHelper();
        this.difficulty = Difficulty.EASY;
    }

    @FXML
    private void selectMediumDifficulty() {
        selectionDifficultyHelper();
        this.difficulty = Difficulty.MEDIUM;
    }

    @FXML
    private void selectHardDifficulty() {
        selectionDifficultyHelper();
        this.difficulty = Difficulty.HARD;

    }

    @FXML
    private void selectWizardArchetype() {
        selectionArchetypeHelper();
        this.archetype = Archetype.WIZARD;
    }

    @FXML
    private void selectMageArchetype() {
        selectionArchetypeHelper();
        this.archetype = Archetype.MAGE;

    }

    @FXML
    private void selectWarriorArchetype() {
        selectionArchetypeHelper();
        this.archetype = Archetype.WARRIOR;

    }

    public void selectionDifficultyHelper() {
        toggleGroup1.selectedToggleProperty().addListener((obsVal, oldVal, newVal) -> {
            if (newVal == null) {
                oldVal.setSelected(true);
            }
        });
        easyButton.setToggleGroup(this.toggleGroup1);
        mediumButton.setToggleGroup(this.toggleGroup1);
        hardButton.setToggleGroup(this.toggleGroup1);
    }

    public void selectionArchetypeHelper() {
        toggleGroup2.selectedToggleProperty().addListener((obsVal, oldVal, newVal) -> {
            if (newVal == null) {
                oldVal.setSelected(true);
            }
        });
        warriorButton.setToggleGroup(this.toggleGroup2);
        wizardButton.setToggleGroup(this.toggleGroup2);
        mageButton.setToggleGroup(this.toggleGroup2);
    }

    public AppService getAppService() {
        return appService;
    }

    public SecondaryController setAppService(AppService appService) {
        this.appService = appService;
        return this;
    }

    //Class builders
    public SecondaryController setUsername(String username) {
        this.username = username;
        return this;
    }

    public SecondaryController setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
        return this;
    }

    public SecondaryController setArchetype(Archetype archetype) {
        this.archetype = archetype;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public Archetype getArchetype() {
        return archetype;
    }

    public Scene getScene() {
        return scene;
    }

    public SecondaryController setScene(Scene scene) {
        this.scene = scene;
        return this;
    }
}