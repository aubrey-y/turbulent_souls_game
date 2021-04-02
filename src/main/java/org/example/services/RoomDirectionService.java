package org.example.services;

import org.example.controllers.rooms.BossRoomController;
import org.example.controllers.rooms.Castle1Controller;
import org.example.controllers.rooms.Castle2Controller;
import org.example.controllers.rooms.Castle3Controller;
import org.example.controllers.rooms.CastleTraderController;
import org.example.controllers.rooms.Forest2Controller;
import org.example.controllers.rooms.ForestTraderController;
import org.example.controllers.rooms.Garden1Controller;
import org.example.controllers.rooms.Garden2Controller;
import org.example.controllers.rooms.GardenTraderController;
import org.example.dto.RandomRoom;
import org.example.dto.Room;
import org.example.dto.RoomIdDirectionKey;
import org.example.enums.Direction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.example.enums.Direction.DOWN;
import static org.example.enums.Direction.LEFT;
import static org.example.enums.Direction.RIGHT;
import static org.example.enums.Direction.UP;
import static org.example.enums.RoomType.*;

public class RoomDirectionService {

    private DirectionService directionService;

    private final Map<RoomIdDirectionKey, Room> roomMapping = new HashMap<>();

    private final RandomRoom castleBossStartRoom = new RandomRoom(
            CASTLE3,
            List.of(new Room(CASTLE3)
                            .setLeft(new Room())
                            .setDown(new Room())
                            .setId(19)
                            .setControllerClass(Castle3Controller.class)
                            .setRoot("castleBossStartLeft.fxml"),
                    new Room(CASTLE3)
                            .setUp(new Room())
                            .setDown(new Room())
                            .setId(20)
                            .setControllerClass(Castle3Controller.class)
                            .setRoot("castleBossStartUp.fxml"),
                    new Room(CASTLE3)
                            .setRight(new Room())
                            .setDown(new Room())
                            .setId(21)
                            .setControllerClass(Castle3Controller.class)
                            .setRoot("castleBossStartRight.fxml"))
    );

    public RoomDirectionService(DirectionService directionService) {
        this.directionService = directionService;

        this.initializeForestRooms();
        this.initializeCastleRooms();
        this.initializeGardenRooms();
        this.initializeBossRooms();
    }

    public Room getRoomForRoomAndDirection(Room room, Direction direction) {
        Room target = this.roomMapping.get(new RoomIdDirectionKey(room.getId(), direction));
        if (target instanceof RandomRoom) {
            target = ((RandomRoom) target).getRandomRoom();
        }
        switch (this.directionService.getOppositeDirection(direction)) {
        case UP:
            target.setUp(room);
            break;
        case DOWN:
            target.setDown(room);
            break;
        case RIGHT:
            target.setRight(room);
            break;
        case LEFT:
            target.setLeft(room);
            break;
        default:
            break;
        }
        return target;
    }

    private void initializeForestRooms() {
        this.roomMapping.put(new RoomIdDirectionKey(0, LEFT), new Room(FOREST2)
                .setUp(new Room())
                .setRight(new Room())
                .setDown(new Room())
                .setId(1)
                .setControllerClass(Forest2Controller.class)
                .setRoot("forestLeft.fxml"));
        this.roomMapping.put(new RoomIdDirectionKey(0, UP), new Room(FOREST2)
                .setUp(new Room())
                .setDown(new Room())
                .setId(2)
                .setControllerClass(Forest2Controller.class)
                .setRoot("forestUp.fxml"));
        this.roomMapping.put(new RoomIdDirectionKey(0, RIGHT), new Room(FOREST2)
                .setUp(new Room())
                .setLeft(new Room())
                .setDown(new Room())
                .setId(3)
                .setControllerClass(Forest2Controller.class)
                .setRoot("forestRight.fxml"));
        this.roomMapping.put(new RoomIdDirectionKey(0, DOWN), new Room(FOREST_TRADER)
                .setUp(new Room())
                .setId(4)
                .setControllerClass(ForestTraderController.class)
                .setRoot("forestTrader.fxml"));

        //This represents room 1 on the diagram
        this.roomMapping.put(new RoomIdDirectionKey(1, DOWN), new Room(FOREST_TRADER)
                .setUp(new Room())
                .setId(5)
                .setControllerClass(ForestTraderController.class)
                .setRoot("forestTrader.fxml"));
        this.roomMapping.put(new RoomIdDirectionKey(3, DOWN), new Room(FOREST_TRADER)
                .setUp(new Room())
                .setId(6)
                .setControllerClass(ForestTraderController.class)
                .setRoot("forestTrader.fxml"));
    }

    private void initializeCastleRooms() {
        this.roomMapping.put(new RoomIdDirectionKey(1, UP), new Room(CASTLE1)
                .setUp(new Room())
                .setLeft(new Room())
                .setRight(new Room())
                .setDown(new Room())
                .setId(7)
                .setControllerClass(Castle1Controller.class)
                .setRoot("castleStart.fxml"));

        //This represents room 2 on the diagram
        this.roomMapping.put(new RoomIdDirectionKey(2, UP), new Room(CASTLE1)
                .setUp(new Room())
                .setLeft(new Room())
                .setRight(new Room())
                .setDown(new Room())
                .setId(7)
                .setControllerClass(Castle1Controller.class)
                .setRoot("castleStart.fxml"));

        //This represents room 3 on the diagram
        this.roomMapping.put(new RoomIdDirectionKey(3, UP), new Room(CASTLE1)
                .setUp(new Room())
                .setLeft(new Room())
                .setRight(new Room())
                .setDown(new Room())
                .setId(7)
                .setControllerClass(Castle1Controller.class)
                .setRoot("castleStart.fxml"));

        //This represents room 7 on the diagram
        this.roomMapping.put(new RoomIdDirectionKey(7, UP), new Room(CASTLE_TRADER)
                .setDown(new Room())
                .setId(10)
                .setControllerClass(CastleTraderController.class)
                .setRoot("castleTrader.fxml"));
        this.roomMapping.put(new RoomIdDirectionKey(7, LEFT), new Room(CASTLE2)
                .setUp(new Room())
                .setDown(new Room())
                .setRight(new Room())
                .setId(8)
                .setControllerClass(Castle2Controller.class)
                .setRoot("castleLeft.fxml"));
        this.roomMapping.put(new RoomIdDirectionKey(7, RIGHT), new Room(CASTLE2)
                .setUp(new Room())
                .setDown(new Room())
                .setLeft(new Room())
                .setId(9)
                .setControllerClass(Castle2Controller.class)
                .setRoot("castleRight.fxml"));

        //This represents room 8 on the diagram
        this.roomMapping.put(new RoomIdDirectionKey(8, UP), new Room(CASTLE_TRADER)
                .setDown(new Room())
                .setId(11)
                .setControllerClass(CastleTraderController.class)
                .setRoot("castleTrader.fxml"));

        //This represents room 9 on the diagram
        this.roomMapping.put(new RoomIdDirectionKey(9, UP), new Room(CASTLE_TRADER)
                .setDown(new Room())
                .setId(12)
                .setControllerClass(CastleTraderController.class)
                .setRoot("castleTrader.fxml"));
    }

    private void initializeGardenRooms() {
        this.roomMapping.put(new RoomIdDirectionKey(8, DOWN), new Room(GARDEN1)
                .setUp(new Room())
                .setLeft(new Room())
                .setRight(new Room())
                .setDown(new Room())
                .setId(13)
                .setControllerClass(Garden1Controller.class)
                .setRoot("gardenStart.fxml"));


        this.roomMapping.put(new RoomIdDirectionKey(9, DOWN), new Room(GARDEN1)
                .setUp(new Room())
                .setLeft(new Room())
                .setRight(new Room())
                .setDown(new Room())
                .setId(13)
                .setControllerClass(Garden1Controller.class)
                .setRoot("gardenStart.fxml"));

        //This represents room 13 on the diagram
        this.roomMapping.put(new RoomIdDirectionKey(13, LEFT), new Room(GARDEN2)
                .setUp(new Room())
                .setRight(new Room())
                .setDown(new Room())
                .setId(14)
                .setControllerClass(Garden2Controller.class)
                .setRoot("gardenLeft.fxml"));
        this.roomMapping.put(new RoomIdDirectionKey(13, RIGHT), new Room(GARDEN2)
                .setUp(new Room())
                .setLeft(new Room())
                .setDown(new Room())
                .setId(15)
                .setControllerClass(Garden2Controller.class)
                .setRoot("gardenRight.fxml"));
        this.roomMapping.put(new RoomIdDirectionKey(13, DOWN), new Room(GARDEN2)
                .setUp(new Room())
                .setId(16)
                .setControllerClass(GardenTraderController.class)
                .setRoot("gardenTrader.fxml"));

        this.roomMapping.put(new RoomIdDirectionKey(14, DOWN), new Room(GARDEN_TRADER)
                .setUp(new Room())
                .setId(17)
                .setControllerClass(Garden2Controller.class)
                .setRoot("gardenTrader.fxml"));

        //This represents room 15 on the diagram
        this.roomMapping.put(new RoomIdDirectionKey(15, UP), this.castleBossStartRoom
                .setControllerClass(Castle3Controller.class));

        this.roomMapping.put(new RoomIdDirectionKey(15, DOWN), new Room(GARDEN_TRADER)
                .setUp(new Room())
                .setId(18)
                .setControllerClass(GardenTraderController.class)
                .setRoot("gardenTrader.fxml"));
    }

    private void initializeBossRooms() {
        //This represents room 14 on the diagram
        this.roomMapping.put(new RoomIdDirectionKey(14, UP), this.castleBossStartRoom
                .setControllerClass(Castle3Controller.class));

        //Room 19 mappings would go here
        this.roomMapping.put(new RoomIdDirectionKey(20, UP), new Room(BOSS)
                .setDown(new Room())
                .setId(21)
                .setControllerClass(BossRoomController.class)
                .setRoot("castleBossUp.fxml"));
        this.roomMapping.put(new RoomIdDirectionKey(19, LEFT), new Room(BOSS)
                .setRight(new Room())
                .setId(21)
                .setControllerClass(BossRoomController.class)
                .setRoot("castleBossLeft.fxml"));
        this.roomMapping.put(new RoomIdDirectionKey(21, RIGHT), new Room(BOSS)
                .setLeft(new Room())
                .setId(21)
                .setControllerClass(BossRoomController.class)
                .setRoot("castleBossRight.fxml"));
    }

    public DirectionService getDirectionService() {
        return directionService;
    }

    public RoomDirectionService setDirectionService(DirectionService directionService) {
        this.directionService = directionService;
        return this;
    }
}
