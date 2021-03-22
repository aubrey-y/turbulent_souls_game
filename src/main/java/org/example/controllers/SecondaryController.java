package org.example.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import org.example.App;
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

import static org.example.enums.RoomType.FOREST1;
import static org.example.exceptions.ExceptionMessages.INVALID_ARCHETYPE_EXCEPTION_MESSAGE;
import static org.example.exceptions.ExceptionMessages.INVALID_DIFFICULTY_EXCEPTION_MESSAGE;
import static org.example.exceptions.ExceptionMessages.INVALID_NAME_EXCEPTION_MESSAGE;
import static org.example.exceptions.ExceptionMessages.UNKNOWN_EXCEPTION_MESSAGE;

public class SecondaryController extends ErrorBaseController {

    private Scene scene;

    private String username;

    private Difficulty difficulty;

    private Archetype archetype;

    public SecondaryController(Scene scene) {
        this.scene = scene;
    }

    public static final Room STARTING_ROOM = new Room(FOREST1)
            .setDown(new Room())
            .setRight(new Room())
            .setUp(new Room())
            .setLeft(new Room())
            .setId(0)
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
            this.appService.setPlayerState(
                    new PlayerState(
                            this.username,
                            this.archetype,
                            this.difficulty,
                            new int[]{400, 540}));
            this.appService.setActiveRoom(STARTING_ROOM);
            FXMLLoader loader = new FXMLLoader(App.class.getResource("gameScreen.fxml"));
            DirectionService directionService = new DirectionService();
            RoomDirectionService roomDirectionService = new RoomDirectionService(directionService);
            HealthService healthService = new HealthService(this.appService);
            loader.setControllerFactory(GameScreenController -> new GameScreenController(
                    this.appService,
                    new PlayerService(this.appService, roomDirectionService, healthService),
                    directionService,
                    roomDirectionService,
                    healthService,
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
        if (this.difficulty != Difficulty.EASY) {
            this.difficulty = Difficulty.EASY;
        } else {
            this.difficulty = null;
        }
    }

    @FXML
    private void selectMediumDifficulty() {
        if (this.difficulty != Difficulty.MEDIUM) {
            this.difficulty = Difficulty.MEDIUM;
        } else {
            this.difficulty = null;
        }
    }

    @FXML
    private void selectHardDifficulty() {
        if (this.difficulty != Difficulty.HARD) {
            this.difficulty = Difficulty.HARD;
        } else {
            this.difficulty = null;
        }
    }

    @FXML
    private void selectArcherArchetype() {
        if (this.archetype != Archetype.ARCHER) {
            this.archetype = Archetype.ARCHER;
        } else {
            this.archetype = null;
        }
    }

    @FXML
    private void selectMageArchetype() {
        if (this.archetype != Archetype.MAGE) {
            this.archetype = Archetype.MAGE;
        } else {
            this.archetype = null;
        }
    }

    @FXML
    private void selectWarriorArchetype() {
        if (this.archetype != Archetype.WARRIOR) {
            this.archetype = Archetype.WARRIOR;
        } else {
            this.archetype = null;
        }
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
}