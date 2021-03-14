package org.example.services;

import javafx.scene.image.ImageView;
import org.example.dto.PlayerState;
import org.example.dto.Room;
import org.example.enums.Direction;
import org.example.enums.RoomType;

import java.util.Arrays;
import java.util.List;

import static org.example.enums.Direction.*;
import static org.example.enums.RoomType.CASTLE_TRADER;
import static org.example.enums.RoomType.FOREST_TRADER;
import static org.example.enums.RoomType.GARDEN_TRADER;

public class PlayerService {

    private ImageView imageView;

    private AppService appService;

    private RoomDirectionService roomDirectionService;

    private static final double MOVE_SIZE = 6;

    private static final List<RoomType> traderRooms =
            Arrays.asList(FOREST_TRADER, CASTLE_TRADER, GARDEN_TRADER);

    public PlayerService(ImageView imageView,
                         AppService appService,
                         RoomDirectionService roomDirectionService) {
        this.imageView = imageView;
        this.appService = appService;
        this.roomDirectionService = roomDirectionService;
    }

    public void moveUp() {
        this.imageView.setTranslateY(this.imageView.getTranslateY() - MOVE_SIZE);
        this.checkForExit();
    }

    public void moveDown() {
        this.imageView.setTranslateY(this.imageView.getTranslateY() + MOVE_SIZE);
        this.checkForExit();
    }

    public void moveRight() {
        this.imageView.setTranslateX(this.imageView.getTranslateX() + MOVE_SIZE);
        this.checkForExit();
    }

    public void moveLeft() {
        this.imageView.setTranslateX(this.imageView.getTranslateX() - MOVE_SIZE);
        this.checkForExit();
    }

    public void moveX(double value) {
        this.imageView.setTranslateX(this.imageView.getTranslateX() + value);
    }

    public void moveY(double value) {
        this.imageView.setTranslateY(this.imageView.getTranslateY() + value);
    }

    public void setVisible(boolean value) {
        this.imageView.setVisible(value);
    }

    private void checkForExit() {
        Direction exitDirection = this.exitDirection();
        if(exitDirection == null) {
            return;
        }
        Room currentRoom = this.appService.getActiveRoom();
        switch (exitDirection) {
            case UP:
                if(currentRoom.getUp() != null) {
                    currentRoom.setUp(this.roomDirectionService
                            .getRoomForRoomAndDirection(currentRoom, exitDirection));
                    this.appService.setActiveRoom(currentRoom.getUp());
                    this.setNewPlayerSpawnCoordinates(exitDirection);
                    this.appService.setRoot(currentRoom.getUp().getRoot());
                }
                break;
            case DOWN:
                if(currentRoom.getDown() != null) {
                    currentRoom.setDown(this.roomDirectionService
                            .getRoomForRoomAndDirection(currentRoom, exitDirection));
                    this.appService.setActiveRoom(currentRoom.getDown());
                    this.setNewPlayerSpawnCoordinates(exitDirection);
                    this.appService.setRoot(currentRoom.getDown().getRoot());
                }
                break;
            case LEFT:
                if(currentRoom.getLeft() != null) {
                    currentRoom.setLeft(this.roomDirectionService
                            .getRoomForRoomAndDirection(currentRoom, exitDirection));
                    this.appService.setActiveRoom(currentRoom.getLeft());
                    this.setNewPlayerSpawnCoordinates(exitDirection);
                    this.appService.setRoot(currentRoom.getLeft().getRoot());
                }
                break;
            case RIGHT:
                if(currentRoom.getRight() != null) {
                    currentRoom.setDown(this.roomDirectionService
                            .getRoomForRoomAndDirection(currentRoom, exitDirection));
                    this.appService.setActiveRoom(currentRoom.getRight());
                    this.setNewPlayerSpawnCoordinates(exitDirection);
                    this.appService.setRoot(currentRoom.getRight().getRoot());
                }
                break;
        }
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
        if(this.imageView.getTranslateX() >= 844 && this.imageView.getTranslateX() <= 982 &&
                this.imageView.getTranslateY() >= 912) {
            return DOWN;
        } else if(this.imageView.getTranslateX() <= 22 && this.imageView.getTranslateY() >= 420 &&
                this.imageView.getTranslateY() <= 534) {
            return LEFT;
        } else if(this.imageView.getTranslateX() >= 1774 && this.imageView.getTranslateY() >= 420 &&
                this.imageView.getTranslateY() <= 534) {
            return RIGHT;
        } else if(this.imageView.getTranslateX() >= 844 && this.imageView.getTranslateX() <= 982 &&
                this.imageView.getTranslateY() <= 24) {
            return UP;
        }
        return null;
    }
}
