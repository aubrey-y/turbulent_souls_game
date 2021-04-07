package org.example;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.example.controllers.rooms.Forest1Controller;
import org.example.controllers.rooms.Forest2Controller;
import org.example.controllers.rooms.ForestTraderController;
import org.example.dto.Monster;
import org.example.dto.PlayerState;
import org.example.exceptions.PlayerCreationException;
import org.example.services.*;
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
import java.util.HashSet;
import java.util.concurrent.TimeoutException;

import static org.example.ConstantUtil.ONE_SECOND;
import static org.example.controllers.SecondaryController.STARTING_ROOM;
import static org.example.enums.Archetype.WARRIOR;
import static org.example.enums.Difficulty.EASY;
import static org.example.services.PlayerService.MOVE_SIZE;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
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

    private MonsterService monsterService;

    private final int[] spawnCoordinates = new int[]{400, 540};

    @Start
    public void setUp(Stage stage) throws IOException {
        this.appService = spy(AppService.class);
        this.healthService = new HealthService(this.appService);
        this.directionService = new DirectionService();
        this.roomDirectionService = new RoomDirectionService(this.directionService);
        this.playerService = new PlayerService(
                this.appService, this.roomDirectionService, this.healthService, mock(SaveService.class));
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
                mock(SaveService.class),
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
        this.withMockedMonsterService();
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
        Forest2Controller forest2Controller = App.getActiveLoader().getController();
        //Then
        assertThat(forest2Controller.getPlayer().getTranslateX(),
                is(not(startX - MOVE_SIZE * times)));
        assertThat(forest2Controller.getPlayer().getTranslateY(),
                is(startY));
    }

    @Test
    public void testSwitchRight(FxRobot robot) {
        //Given
        this.withMockedMonsterService();
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
        Forest2Controller forest2Controller = App.getActiveLoader().getController();
        //Then
        assertThat(forest2Controller.getPlayer().getTranslateX(),
                is(not(startX - MOVE_SIZE * times)));
        assertThat(forest2Controller.getPlayer().getTranslateY(),
                is(startY));
    }

    @Test
    public void testSwitchUp(FxRobot robot) {
        //Given
        this.withMockedMonsterService();
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
        Forest2Controller forest2Controller = App.getActiveLoader().getController();
        //Then
        assertThat(forest2Controller.getPlayer().getTranslateY(),
                is(not(startY - MOVE_SIZE * times)));
        assertThat(forest2Controller.getPlayer().getTranslateX(),
                is(startX));
    }

    @Test
    public void testSwitchDown(FxRobot robot) {
        //Given
        this.withMockedMonsterService();
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
        ForestTraderController forestTraderController = App.getActiveLoader().getController();
        //Then
        assertThat(forestTraderController.getPlayer().getTranslateY(),
                is(not(startY - MOVE_SIZE * times)));
        assertThat(forestTraderController.getPlayer().getTranslateX(),
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

    //Milestone 4 Tests Begin Here
    @Test
    public void testPlayerHealthDecrease(FxRobot robot) {
        double startX = 1500;
        double startY = 400;

        playerService.moveX(startX);
        playerService.moveY(startY);

        // Make new slime - stronger attack to make test quicker
        Monster slime1 = this.controller.getSlime1();
        slime1.setAccuracy(1.0);
        this.controller.getMonsterService().addMonster(Forest1Controller.SLIME_1_KEY, slime1);

        robot.sleep(ONE_SECOND + 100);

        assertThat(this.controller.getAppService().getPlayerState().getHealth(),
                anyOf(equalTo(this.controller
                                .getAppService()
                                .getPlayerState()
                                .getHealthCapacity() - slime1.getAttack()),
                        equalTo(this.controller
                                .getAppService()
                                .getPlayerState()
                                .getHealthCapacity() -  (2 * slime1.getAttack()))));
    }

    @Test
    public void testPlayerDeath(FxRobot robot) {
        double startX = 1500;
        double startY = 400;

        playerService.moveX(startX);
        playerService.moveY(startY);

        // Make new slime - stronger attack to make test quicker
        Monster slime1 = this.controller.getSlime1();
        slime1.setAttack(100);
        slime1.setAccuracy(1.0);
        this.controller.getMonsterService().addMonster(Forest1Controller.SLIME_1_KEY, slime1);

        robot.sleep(ONE_SECOND + 100);

        // Check that player health is 0
        assertThat(this.controller
                        .getAppService()
                        .getPlayerState()
                        .getHealthCapacity() - slime1.getAttack(),
                is(equalTo(0.0)));
    }

    @Test
    public void testRoomLockLeft(FxRobot robot) {
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
        //Then
        assertThat(this.controller.getPlayer().getTranslateX(),
                is(startX - MOVE_SIZE * times));
        assertThat(this.controller.getPlayer().getTranslateY(),
                is(startY));
    }

    @Test
    public void testRoomLockRight(FxRobot robot) {
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
        //Then
        assertThat(this.controller.getPlayer().getTranslateX(),
                is(startX + MOVE_SIZE * times));
        assertThat(this.controller.getPlayer().getTranslateY(),
                is(startY));
    }

    @Test
    public void testRoomLockUp(FxRobot robot) {
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
        //Then
        assertThat(this.controller.getPlayer().getTranslateY(),
                is(startY - MOVE_SIZE * times));
        assertThat(this.controller.getPlayer().getTranslateX(),
                is(startX));
    }

    @Test
    public void testRoomLockDown(FxRobot robot) {
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
        //Then
        assertThat(this.controller.getPlayer().getTranslateY(),
                is(startY + MOVE_SIZE * times));
        assertThat(this.controller.getPlayer().getTranslateX(),
                is(startX));
    }

    private void withMockedAppService() {
        doAnswer(invocationOnMock -> {
            this.scene.setRoot(((FXMLLoader) invocationOnMock.getArgument(0)).load());
            return null;
        }).when(this.appService).setRoot(any());
        doReturn(this.scene).when(this.appService).getScene();
        doReturn(this.getPlayerState()).when(this.appService).getPlayerState();
        doReturn(STARTING_ROOM).when(this.appService).getActiveRoom();
        doReturn(new HashSet<>()).when(this.appService).getMonstersKilled();
    }

    private void withMockedMonsterService() {
        this.monsterService = spy(MonsterService.class);
        this.controller.setMonsterService(this.monsterService);
        this.controller.getPlayerService().setMonsterService(this.monsterService);
        doReturn(0).when(this.monsterService).getMonstersRemaining();
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
