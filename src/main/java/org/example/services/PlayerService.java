package org.example.services;

import javafx.scene.image.ImageView;
import org.example.dto.Room;
import org.example.enums.Direction;
import org.example.enums.RoomType;

import java.util.Arrays;
import java.util.List;

import static org.example.enums.Direction.DOWN;
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
//                if(currentRoom.getUp() != null) {
//                    if(currentRoom.getUp().getRoot() == null) {
//                        this.appService.set
//                    }
//                }
                break;
            case DOWN:
                if(currentRoom.getDown() != null) {
                    currentRoom.setDown(this.roomDirectionService
                            .getRoomForRoomAndDirection(currentRoom, DOWN));
                    this.appService.setActiveRoom(currentRoom.getDown());
                    this.appService.setRoot(currentRoom.getDown().getRoot());
                }
                break;
        }
    }

    private Direction exitDirection() {
        System.out.println(this.imageView.getTranslateX());
        System.out.println(this.imageView.getTranslateY());
        System.out.println("_________________________");
        if(this.imageView.getTranslateX() >= 875 && this.imageView.getTranslateX() <= 1120 &&
                this.imageView.getTranslateY() >= 1000) {
            return DOWN;
        }
        return null;
    }
}
