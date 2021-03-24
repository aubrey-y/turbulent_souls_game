package org.example;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.example.controllers.GameScreenController;
import org.example.controllers.rooms.Forest1Controller;
import org.example.dto.PlayerState;
import org.example.exceptions.PlayerCreationException;
import org.example.services.AppService;
import org.example.services.DirectionService;
import org.example.services.HealthService;
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

import static org.example.controllers.SecondaryController.STARTING_ROOM;
import static org.example.enums.Archetype.WARRIOR;
import static org.example.enums.Difficulty.EASY;
import static org.example.services.PlayerService.MOVE_SIZE;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

@Disabled
@ExtendWith(ApplicationExtension.class)
public class Forest1ControllerTest {

    private Forest1Controller controller;

    private Scene scene;

    @Spy
    private AppService appService;

    private PlayerService playerService;

    private DirectionService directionService;

    private RoomDirectionService roomDirectionService;

    private HealthService healthService;

    private final int[] spawnCoordinates = new int[]{400, 540};

    @Start
    public void setUp(Stage stage) throws IOException {
        this.appService = spy(AppService.class);
        this.healthService = new HealthService(this.appService);
        this.directionService = new DirectionService();
        this.roomDirectionService = new RoomDirectionService(this.directionService);
        this.playerService = new PlayerService(
                this.appService, this.roomDirectionService, this.healthService);
        withMockedAppService();
        FXMLLoader loader = new FXMLLoader(App.class.getResource("gameScreen.fxml"));
        Scene mockedScene = new Scene(
                new FXMLLoader(App.class.getResource("primary.fxml")).load());
        loader.setControllerFactory(GameScreenController -> new Forest1Controller(
                this.appService,
                this.playerService,
                this.directionService,
                this.roomDirectionService,
                this.healthService,
                mockedScene));
        Parent root = loader.load();
        this.scene = new Scene(root, 1920, 1080);
        withMockedAppService();
        this.controller = loader.getController();
        this.controller.setScene(this.scene);
        this.controller.initialize(null, null);
        stage.setScene(this.scene);
        stage.show();
    }

    @AfterEach
    public void tearDown() throws TimeoutException {
        FxToolkit.hideStage();
    }
    @Test
    public void testSwitchLeft(FxRobot robot) {
        //Given
        int times = 5;
        double startX = 22.0;
        double startY = 444.0;
        //When
        playerService.moveX(startX);
        playerService.moveY(startY);
        for (int i = 0; i < times; i++) {
            robot.press(KeyCode.A);
            robot.release(KeyCode.A);
        }
        this.controller = App.getActiveLoader().getController();
        //Then
        assertThat(this.controller.getPlayer().getTranslateX(),
                is(not(startX - MOVE_SIZE * times)));
        assertThat(this.controller.getPlayer().getTranslateY(),
                is(startY));
    }

    @Test
    public void testSwitchRight(FxRobot robot) {
        //Given
        int times = 5;
        double startX = 1764.0;
        double startY = 534.0;
        //When
        playerService.moveX(startX);
        playerService.moveY(startY);
        for (int i = 0; i < times; i++) {
            robot.press(KeyCode.D);
            robot.release(KeyCode.D);
        }
        this.controller = App.getActiveLoader().getController();
        //Then
        assertThat(this.controller.getPlayer().getTranslateX(),
                is(not(startX - MOVE_SIZE * times)));
        assertThat(this.controller.getPlayer().getTranslateY(),
                is(startY));
    }

    @Test
    public void testSwitchUp(FxRobot robot) {
        //Given
        int times = 5;
        double startX = 900.0;
        double startY = 24.0;
        //When
        playerService.moveX(startX);
        playerService.moveY(startY);
        for (int i = 0; i < times; i++) {
            robot.press(KeyCode.W);
            robot.release(KeyCode.W);
        }
        this.controller = App.getActiveLoader().getController();
        //Then
        assertThat(this.controller.getPlayer().getTranslateY(),
                is(not(startY - MOVE_SIZE * times)));
        assertThat(this.controller.getPlayer().getTranslateX(),
                is(startX));
    }

    @Test
    public void testSwitchDown(FxRobot robot) {
        //Given
        int times = 5;
        double startX = 900.0;
        double startY = 904.0;
        //When
        playerService.moveX(startX);
        playerService.moveY(startY);
        for (int i = 0; i < times; i++) {
            robot.press(KeyCode.S);
            robot.release(KeyCode.S);
        }
        this.controller = App.getActiveLoader().getController();
        //Then
        assertThat(this.controller.getPlayer().getTranslateY(),
                is(not(startY - MOVE_SIZE * times)));
        assertThat(this.controller.getPlayer().getTranslateX(),
                is(startX));
    }

    @Test
    public void testPlayerBasicMovementDown(FxRobot robot) {
        //Given
        int times = 3;
        //When
        for (int i = 0; i < times; i++) {
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
        for (int i = 0; i < times; i++) {
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
        for (int i = 0; i < times; i++) {
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
    public void testPlayerBasicMovementCombination1(FxRobot robot) {
        //Given
        int times = 3;
        //When
        for (int i = 0; i < times; i++) {
            robot.press(KeyCode.D);
            robot.release(KeyCode.D);
            robot.press(KeyCode.W);
            robot.release(KeyCode.W);
        }
        //Then
        assertThat(this.controller.getPlayer().getTranslateX(),
                is(this.spawnCoordinates[0] + MOVE_SIZE * times));
        assertThat(this.controller.getPlayer().getTranslateY(),
                is(this.spawnCoordinates[1] - MOVE_SIZE * times));
    }

    @Test
    public void testPlayerBasicMovementCombination2(FxRobot robot) {
        //Given
        int times = 5;
        //When
        for (int i = 0; i < times; i++) {
            robot.press(KeyCode.A);
            robot.release(KeyCode.A);
            robot.press(KeyCode.S);
            robot.release(KeyCode.S);
        }
        //Then
        assertThat(this.controller.getPlayer().getTranslateX(),
                is(this.spawnCoordinates[0] - MOVE_SIZE * times));
        assertThat(this.controller.getPlayer().getTranslateY(),
                is(this.spawnCoordinates[1] + MOVE_SIZE * times));
    }

    @Test
    public void testPlayerBasicMovementLeft(FxRobot robot) {
        //Given
        int times = 3;
        //When
        for (int i = 0; i < times; i++) {
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
        doAnswer(invocationOnMock -> {
            this.scene.setRoot(((FXMLLoader) invocationOnMock.getArgument(0)).load());
            return null;
        }).when(this.appService).setRoot(any());
        doReturn(this.scene).when(this.appService).getScene();
        doReturn(this.getPlayerState()).when(this.appService).getPlayerState();
        doReturn(STARTING_ROOM).when(this.appService).getActiveRoom();
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
