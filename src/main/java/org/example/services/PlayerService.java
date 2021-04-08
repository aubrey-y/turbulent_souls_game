package org.example.services;

import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import org.example.App;
import org.example.dto.Coordinate;
import org.example.dto.PlayerState;
import org.example.dto.Room;
import org.example.enums.Direction;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

import static org.example.enums.Direction.*;

public class PlayerService {

    private ImageView imageView;

    private AppService appService;

    private RoomDirectionService roomDirectionService;

    private HealthService healthService;

    private MonsterService monsterService;

    private SaveService saveService;

    private DirectionService directionService = new DirectionService();

    private Set<Timeline> controllerTimelines;

    private Direction lastExitDirection;

    public static final double DEFAULT_MOVE_SIZE = 30;

    private double moveSize = DEFAULT_MOVE_SIZE;

    public PlayerService(AppService appService,
                         RoomDirectionService roomDirectionService,
                         HealthService healthService,
                         SaveService saveService) {
        this.appService = appService;
        this.roomDirectionService = roomDirectionService;
        this.healthService = healthService;
        this.saveService = saveService;
        this.controllerTimelines = new HashSet<>();
    }

    public void moveUp(boolean shift) {
        this.imageView.setTranslateY(
                this.imageView.getTranslateY() - (!shift ? this.moveSize : this.moveSize * 2));
        this.checkForExit();
    }

    public void moveDown(boolean shift) {
        this.imageView.setTranslateY(
                this.imageView.getTranslateY() + (!shift ? this.moveSize : this.moveSize * 2));
        this.checkForExit();
    }

    public void moveRight(boolean shift) {
        this.imageView.setTranslateX(
                this.imageView.getTranslateX() + (!shift ? this.moveSize : this.moveSize * 2));
        this.checkForExit();
    }

    public void moveLeft(boolean shift) {
        this.imageView.setTranslateX(
                this.imageView.getTranslateX() - (!shift ? this.moveSize : this.moveSize * 2));
        this.checkForExit();
    }

    public void moveX(double value) {
        this.imageView.setTranslateX(value);
    }

    public void moveY(double value) {
        this.imageView.setTranslateY(value);
    }

    public void setVisible(boolean value) {
        this.imageView.setVisible(value);
    }

    private void checkForExit() {
        Direction exitDirection = this.exitDirection();
        if (exitDirection == null || (this.monsterService.getMonstersRemaining() > 0
                        && this.directionService.getOppositeDirection(
                                this.lastExitDirection) != exitDirection)) {
            return;
        }
        this.lastExitDirection = exitDirection;
        this.terminateExistingTimelines();
        Room currentRoom = this.appService.getActiveRoom();
        switch (exitDirection) {
        case UP:
            if (currentRoom.getUp() != null) {
                if (currentRoom.getUp().getRoot() == null) {
                    currentRoom.setUp(this.roomDirectionService
                            .getRoomForRoomAndDirection(currentRoom, exitDirection));
                }
                this.appService.setActiveRoom(currentRoom.getUp());
                this.setNewPlayerSpawnCoordinates(exitDirection);
                this.appService.setRoot(this.getLoader(currentRoom.getUp()));
            }
            break;
        case DOWN:
            if (currentRoom.getDown() != null) {
                if (currentRoom.getDown().getRoot() == null) {
                    currentRoom.setDown(this.roomDirectionService
                            .getRoomForRoomAndDirection(currentRoom, exitDirection));
                }
                this.appService.setActiveRoom(currentRoom.getDown());
                this.setNewPlayerSpawnCoordinates(exitDirection);
                this.appService.setRoot(this.getLoader(currentRoom.getDown()));
            }
            break;
        case LEFT:
            if (currentRoom.getLeft() != null) {
                if (currentRoom.getLeft().getRoot() == null) {
                    currentRoom.setLeft(this.roomDirectionService
                            .getRoomForRoomAndDirection(currentRoom, exitDirection));
                }
                this.appService.setActiveRoom(currentRoom.getLeft());
                this.setNewPlayerSpawnCoordinates(exitDirection);
                this.appService.setRoot(this.getLoader(currentRoom.getLeft()));
            }
            break;
        case RIGHT:
            if (currentRoom.getRight() != null) {
                if (currentRoom.getRight().getRoot() == null) {
                    currentRoom.setRight(this.roomDirectionService
                            .getRoomForRoomAndDirection(currentRoom, exitDirection));
                }
                this.appService.setActiveRoom(currentRoom.getRight());
                this.setNewPlayerSpawnCoordinates(exitDirection);
                this.appService.setRoot(this.getLoader(currentRoom.getRight()));
            }
            break;
        default:
            break;
        }
    }

    public void terminateExistingTimelines() {
        for (Timeline timeline: this.controllerTimelines) {
            timeline.stop();
        }
        this.controllerTimelines.clear();
    }

    private FXMLLoader getLoader(Room room) {
        FXMLLoader loader = new FXMLLoader(App.class.getResource(room.getRoot()));
        Constructor<?> controllerConstructor;
        try {
            controllerConstructor = room.getControllerClass().getConstructor(
                    AppService.class,
                    PlayerService.class,
                    DirectionService.class,
                    RoomDirectionService.class,
                    HealthService.class,
                    SaveService.class,
                    Scene.class);
            loader.setControllerFactory(GameScreenController -> {
                try {
                    return controllerConstructor.newInstance(this.appService,
                            this,
                            this.roomDirectionService.getDirectionService(),
                            this.roomDirectionService,
                            this.healthService,
                            this.saveService,
                            this.appService.getScene());
                } catch (InstantiationException | InvocationTargetException
                        | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        App.setActiveLoader(loader);
        return loader;
    }

    private void setNewPlayerSpawnCoordinates(Direction exitDirection) {
        PlayerState playerState = this.appService.getPlayerState();
        switch (exitDirection) {
        case UP:
            playerState.setSpawnCoordinates(
                    new Coordinate().setX((int) this.imageView.getTranslateX()).setY(910));
            break;
        case DOWN:
            playerState.setSpawnCoordinates(
                    new Coordinate().setX((int) this.imageView.getTranslateX()).setY(30));
            break;
        case LEFT:
            playerState.setSpawnCoordinates(
                    new Coordinate().setX(1770).setY((int) this.imageView.getTranslateY()));
            playerState.setSpawnOrientation(LEFT);
            break;
        case RIGHT:
            playerState.setSpawnCoordinates(
                    new Coordinate().setX(22).setY((int) this.imageView.getTranslateY()));
            playerState.setSpawnOrientation(RIGHT);
            break;
        default:
            break;
        }
        this.appService.setPlayerState(playerState);
    }

    private Direction exitDirection() {
        System.out.println(this.imageView.getTranslateX());
        System.out.println(this.imageView.getTranslateY());
        System.out.println("_________________________");
        if (this.imageView.getTranslateX() >= 844 && this.imageView.getTranslateX() <= 982
                && this.imageView.getTranslateY() >= 912) {
            return DOWN;
        } else if (this.imageView.getTranslateX() <= 22 && this.imageView.getTranslateY() >= 420
                && this.imageView.getTranslateY() <= 534) {
            return LEFT;
        } else if (this.imageView.getTranslateX() >= 1774 && this.imageView.getTranslateY() >= 420
                && this.imageView.getTranslateY() <= 556) {
            return RIGHT;
        } else if (this.imageView.getTranslateX() >= 844 && this.imageView.getTranslateX() <= 982
                && this.imageView.getTranslateY() <= 24) {
            return UP;
        }
        return null;
    }

    public void registerTimeline(Timeline timeline) {
        this.controllerTimelines.add(timeline);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public PlayerService setImageView(ImageView imageView) {
        this.imageView = imageView;
        return this;
    }

    public AppService getAppService() {
        return appService;
    }

    public PlayerService setAppService(AppService appService) {
        this.appService = appService;
        return this;
    }

    public HealthService getHealthService() {
        return healthService;
    }

    public PlayerService setHealthService(HealthService healthService) {
        this.healthService = healthService;
        return this;
    }

    public RoomDirectionService getRoomDirectionService() {
        return roomDirectionService;
    }

    public PlayerService setRoomDirectionService(RoomDirectionService roomDirectionService) {
        this.roomDirectionService = roomDirectionService;
        return this;
    }

    public MonsterService getMonsterService() {
        return monsterService;
    }

    public PlayerService setMonsterService(MonsterService monsterService) {
        this.monsterService = monsterService;
        return this;
    }

    public void setMoveSize(double moveSize) {
        this.moveSize = moveSize;
    }

    public double getMoveSize() {
        return this.moveSize;
    }
}
