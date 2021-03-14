package org.example;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.controllers.GameScreenController;
import org.example.dto.PlayerState;
import org.example.exceptions.PlayerCreationException;
import org.example.services.AppService;
import org.example.services.DirectionService;
import org.example.services.PlayerService;
import org.example.services.RoomDirectionService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;

import static org.example.enums.Archetype.WARRIOR;
import static org.example.enums.Difficulty.EASY;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

@Disabled
@ExtendWith(ApplicationExtension.class)
public class GameScreenControllerTest {

    @Spy
    private AppService appService;

    private PlayerService playerService;

    private DirectionService directionService;

    private RoomDirectionService roomDirectionService;

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
        GameScreenController controller = loader.getController();
        controller.setScene(scene);
        controller.initialize(null, null);
        stage.setScene(scene);
        stage.show();
    }

//    @Test
//    public void testSwitchToTrader(FxRobot robot) {
//        //When
//        robot.press(KeyCode.D);
//        robot.press(KeyCode.D);
//        robot.press(KeyCode.D);
//        //Then
//        //verifyThat("#errorText", hasText(INVALID_DIFFICULTY_EXCEPTION_MESSAGE));
//    }

    private void withMockedAppService() {
        doNothing().when(this.appService).setRoot(any());
        doReturn(this.getPlayerState()).when(this.appService).getPlayerState();
    }

    private PlayerState getPlayerState() {
        PlayerState playerState;
        try {
            playerState = new PlayerState("Jimmy", WARRIOR, EASY);
        } catch (PlayerCreationException e) {
            throw new RuntimeException(e.getMessage());
        }
        return playerState;
    }
}
