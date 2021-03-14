package org.example;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.example.controllers.GameScreenController;
import org.example.dto.PlayerState;
import org.example.exceptions.PlayerCreationException;
import org.example.services.AppService;
import org.example.services.DirectionService;
import org.example.services.PlayerService;
import org.example.services.RoomDirectionService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static org.example.enums.Archetype.WARRIOR;
import static org.example.enums.Difficulty.EASY;
import static org.example.services.PlayerService.MOVE_SIZE;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

@Disabled
@ExtendWith(ApplicationExtension.class)
public class GameScreenControllerTest {

    private GameScreenController controller;

    @Spy
    private AppService appService;

    private PlayerService playerService;

    private DirectionService directionService;

    private RoomDirectionService roomDirectionService;

    private final int[] spawnCoordinates = new int[]{400, 540};

    @Start
    public void setUp(Stage stage) throws IOException {
        this.appService = spy(AppService.class);
        this.playerService = new PlayerService(this.appService, this.roomDirectionService);
        this.directionService = new DirectionService();
        this.roomDirectionService = new RoomDirectionService(this.directionService);
        withMockedAppService();
        FXMLLoader loader = new FXMLLoader(App.class.getResource("gameScreen.fxml"));
        Scene mockedScene = new Scene(new FXMLLoader(App.class.getResource("primary.fxml")).load());
        loader.setControllerFactory(GameScreenController -> new GameScreenController(
                this.appService,
                this.playerService,
                this.directionService,
                this.roomDirectionService,
                mockedScene));
        Parent root = loader.load();
        Scene scene = new Scene(root, 0, 0);
        this.controller = loader.getController();
        this.controller.setScene(scene);
        this.controller.initialize(null, null);
        stage.setScene(scene);
        stage.show();
    }

    @AfterEach
    public void tearDown() throws TimeoutException {
        FxToolkit.hideStage();
    }

    @Test
    public void testPlayerBasicMovementDown(FxRobot robot) {
        //Given
        int times = 3;
        //When
        for(int i = 0; i < times; i++) {
            robot.press(KeyCode.S);
            robot.release(KeyCode.S);
        }
        //Then
        assertThat(this.controller.getPlayer().getTranslateX(),
                is((double) this.spawnCoordinates[0]));
        assertThat(this.controller.getPlayer().getTranslateY(),
                is(this.spawnCoordinates[1] + MOVE_SIZE * times));
    }

    @Test
    public void testPlayerBasicMovementUp(FxRobot robot) {
        //Given
        int times = 3;
        //When
        for(int i = 0; i < times; i++) {
            robot.press(KeyCode.W);
            robot.release(KeyCode.W);
        }
        //Then
        assertThat(this.controller.getPlayer().getTranslateX(),
                is((double) this.spawnCoordinates[0]));
        assertThat(this.controller.getPlayer().getTranslateY(),
                is(this.spawnCoordinates[1] - MOVE_SIZE * times));
    }

    @Test
    public void testPlayerBasicMovementRight(FxRobot robot) {
        //Given
        int times = 3;
        //When
        for(int i = 0; i < times; i++) {
            robot.press(KeyCode.D);
            robot.release(KeyCode.D);
        }
        //Then
        assertThat(this.controller.getPlayer().getTranslateX(),
                is(this.spawnCoordinates[0] + MOVE_SIZE * times));
        assertThat(this.controller.getPlayer().getTranslateY(),
                is((double) this.spawnCoordinates[1]));
    }

    @Test
    public void testPlayerBasicMovementLeft(FxRobot robot) {
        //Given
        int times = 3;
        //When
        for(int i = 0; i < times; i++) {
            robot.press(KeyCode.A);
            robot.release(KeyCode.A);
        }
        //Then
        assertThat(this.controller.getPlayer().getTranslateX(),
                is(this.spawnCoordinates[0] - MOVE_SIZE * times));
        assertThat(this.controller.getPlayer().getTranslateY(),
                is((double) this.spawnCoordinates[1]));
    }

    private void withMockedAppService() {
        doNothing().when(this.appService).setRoot(any());
        doReturn(this.getPlayerState()).when(this.appService).getPlayerState();
    }

    private PlayerState getPlayerState() {
        PlayerState playerState;
        try {
            playerState = new PlayerState("Jimmy", WARRIOR, EASY, this.spawnCoordinates);
        } catch (PlayerCreationException e) {
            throw new RuntimeException(e.getMessage());
        }
        return playerState;
    }
}
