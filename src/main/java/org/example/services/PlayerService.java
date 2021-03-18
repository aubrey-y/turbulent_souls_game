package org.example.services;

import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import org.example.App;
import org.example.controllers.GameScreenController;
import org.example.dto.PlayerState;
import org.example.dto.Room;
import org.example.enums.Direction;


import static org.example.enums.Direction.*;

public class PlayerService {

    private ImageView imageView;

    private AppService appService;

    private RoomDirectionService roomDirectionService;

    public static final double MOVE_SIZE = 6;

    public PlayerService(AppService appService,
                         RoomDirectionService roomDirectionService) {
        this.appService = appService;
        this.roomDirectionService = roomDirectionService;
    }

    public void moveUp(boolean shift) {
        this.imageView.setTranslateY(
                this.imageView.getTranslateY() - (!shift ? MOVE_SIZE : MOVE_SIZE * 10));
        this.checkForExit();
    }

    public void moveDown(boolean shift) {
        this.imageView.setTranslateY(
                this.imageView.getTranslateY() + (!shift ? MOVE_SIZE : MOVE_SIZE * 10));
        this.checkForExit();
    }

    public void moveRight(boolean shift) {
        this.imageView.setTranslateX(
                this.imageView.getTranslateX() + (!shift ? MOVE_SIZE : MOVE_SIZE * 10));
        this.checkForExit();
    }

    public void moveLeft(boolean shift) {
        this.imageView.setTranslateX(
                this.imageView.getTranslateX() - (!shift ? MOVE_SIZE : MOVE_SIZE * 10));
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
        if (exitDirection == null) {
            return;
        }
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
                    this.appService.setRoot(this.getLoader(currentRoom.getUp().getRoot()));
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
                    this.appService.setRoot(this.getLoader(currentRoom.getDown().getRoot()));
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
                    this.appService.setRoot(this.getLoader(currentRoom.getLeft().getRoot()));
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
                    this.appService.setRoot(this.getLoader(currentRoom.getRight().getRoot()));
                }
                break;
        }
    }

    private FXMLLoader getLoader(String root) {
        FXMLLoader loader = new FXMLLoader(App.class.getResource(root));
        loader.setControllerFactory(GameScreenController -> new GameScreenController(
                this.appService,
                this,
                this.roomDirectionService.getDirectionService(),
                this.roomDirectionService,
                this.appService.getScene()));
        App.setActiveLoader(loader);
        return loader;
    }

    private void setNewPlayerSpawnCoordinates(Direction exitDirection) {
        PlayerState playerState = this.appService.getPlayerState();
        switch (exitDirection) {
            case UP:
                playerState.setSpawnCoordinates(
                        new int[]{(int) this.imageView.getTranslateX(), 910});
                break;
            case DOWN:
                playerState.setSpawnCoordinates(
                        new int[]{(int) this.imageView.getTranslateX(), 30});
                break;
            case LEFT:
                playerState.setSpawnCoordinates(
                        new int[]{1770, (int) this.imageView.getTranslateY()});
                break;
            case RIGHT:
                playerState.setSpawnCoordinates(
                        new int[]{22, (int) this.imageView.getTranslateY()});
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
        } else if(this.imageView.getTranslateX() <= 22 && this.imageView.getTranslateY() >= 420
                && this.imageView.getTranslateY() <= 534) {
            return LEFT;
        } else if(this.imageView.getTranslateX() >= 1774 && this.imageView.getTranslateY() >= 420
                && this.imageView.getTranslateY() <= 556) {
            return RIGHT;
        } else if(this.imageView.getTranslateX() >= 844 && this.imageView.getTranslateX() <= 982
                && this.imageView.getTranslateY() <= 24) {
            return UP;
        }
        return null;
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

    public RoomDirectionService getRoomDirectionService() {
        return roomDirectionService;
    }

    public PlayerService setRoomDirectionService(RoomDirectionService roomDirectionService) {
        this.roomDirectionService = roomDirectionService;
        return this;
    }
}
