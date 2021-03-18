package org.example.services;

import org.example.dto.RandomRoom;
import org.example.dto.Room;
import org.example.enums.Direction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
                            .setRoot("castleBossStartLeft.fxml"),
                    new Room(CASTLE3)
                            .setUp(new Room())
                            .setDown(new Room())
                            .setId(20)
                            .setRoot("castleBossStartUp.fxml"),
                    new Room(CASTLE3)
                            .setRight(new Room())
                            .setDown(new Room())
                            .setId(21)
                            .setRoot("castleBossStartRight.fxml"))
    );

    public RoomDirectionService(DirectionService directionService) {
        this.directionService = directionService;
        this.roomMapping.put(new RoomIdDirectionKey(0, LEFT), new Room(FOREST2)
                .setUp(new Room())
                .setRight(new Room())
                .setDown(new Room())
                .setId(1)
                .setRoot("forestLeft.fxml"));
        this.roomMapping.put(new RoomIdDirectionKey(0, UP), new Room(FOREST2)
                .setUp(new Room())
                .setDown(new Room())
                .setId(2)
                .setRoot("forestUp.fxml"));
        this.roomMapping.put(new RoomIdDirectionKey(0, RIGHT), new Room(FOREST2)
                .setUp(new Room())
                .setLeft(new Room())
                .setDown(new Room())
                .setId(3)
                .setRoot("forestRight.fxml"));
        this.roomMapping.put(new RoomIdDirectionKey(0, DOWN), new Room(FOREST_TRADER)
                .setUp(new Room())
                .setId(4)
                .setRoot("forestTrader.fxml"));

        //This represents room 1 on the diagram
        this.roomMapping.put(new RoomIdDirectionKey(1, DOWN), new Room(FOREST_TRADER)
                .setUp(new Room())
                .setId(5)
                .setRoot("forestTrader.fxml"));
        this.roomMapping.put(new RoomIdDirectionKey(1, UP), new Room(CASTLE1)
                .setUp(new Room())
                .setLeft(new Room())
                .setRight(new Room())
                .setDown(new Room())
                .setId(7)
                .setRoot("castleStart.fxml"));

        //This represents room 2 on the diagram
        this.roomMapping.put(new RoomIdDirectionKey(2, UP), new Room(CASTLE1)
                .setUp(new Room())
                .setLeft(new Room())
                .setRight(new Room())
                .setDown(new Room())
                .setId(7)
                .setRoot("castleStart.fxml"));

        //This represents room 3 on the diagram
        this.roomMapping.put(new RoomIdDirectionKey(3, UP), new Room(CASTLE1)
                .setUp(new Room())
                .setLeft(new Room())
                .setRight(new Room())
                .setDown(new Room())
                .setId(7)
                .setRoot("castleStart.fxml"));
        this.roomMapping.put(new RoomIdDirectionKey(3, DOWN), new Room(FOREST_TRADER)
                .setUp(new Room())
                .setId(6)
                .setRoot("forestTrader.fxml"));

        //This represents room 7 on the diagram
        this.roomMapping.put(new RoomIdDirectionKey(7, UP), new Room(CASTLE_TRADER)
                .setDown(new Room())
                .setId(10)
                .setRoot("castleTrader.fxml"));
        this.roomMapping.put(new RoomIdDirectionKey(7, LEFT), new Room(CASTLE2)
                .setUp(new Room())
                .setDown(new Room())
                .setRight(new Room())
                .setId(8)
                .setRoot("castleLeft.fxml"));
        this.roomMapping.put(new RoomIdDirectionKey(7, RIGHT), new Room(CASTLE2)
                .setUp(new Room())
                .setDown(new Room())
                .setLeft(new Room())
                .setId(9)
                .setRoot("castleRight.fxml"));

        //This represents room 8 on the diagram
        this.roomMapping.put(new RoomIdDirectionKey(8, UP), new Room(CASTLE_TRADER)
                .setDown(new Room())
                .setId(11)
                .setRoot("castleTrader.fxml"));
        this.roomMapping.put(new RoomIdDirectionKey(8, DOWN), new Room(GARDEN1)
                .setUp(new Room())
                .setLeft(new Room())
                .setRight(new Room())
                .setDown(new Room())
                .setId(13)
                .setRoot("gardenStart.fxml"));

        //This represents room 9 on the diagram
        this.roomMapping.put(new RoomIdDirectionKey(9, UP), new Room(CASTLE_TRADER)
                .setDown(new Room())
                .setId(12)
                .setRoot("castleTrader.fxml"));
        this.roomMapping.put(new RoomIdDirectionKey(9, DOWN), new Room(GARDEN1)
                .setUp(new Room())
                .setLeft(new Room())
                .setRight(new Room())
                .setDown(new Room())
                .setId(13)
                .setRoot("gardenStart.fxml"));

        //This represents room 13 on the diagram
        this.roomMapping.put(new RoomIdDirectionKey(13, LEFT), new Room(GARDEN2)
                .setUp(new Room())
                .setRight(new Room())
                .setDown(new Room())
                .setId(14)
                .setRoot("gardenLeft.fxml"));
        this.roomMapping.put(new RoomIdDirectionKey(13, RIGHT), new Room(GARDEN2)
                .setUp(new Room())
                .setLeft(new Room())
                .setDown(new Room())
                .setId(15)
                .setRoot("gardenRight.fxml"));
        this.roomMapping.put(new RoomIdDirectionKey(13, DOWN), new Room(GARDEN2)
                .setUp(new Room())
                .setId(16)
                .setRoot("gardenTrader.fxml"));

        //This represents room 14 on the diagram
        this.roomMapping.put(new RoomIdDirectionKey(14, UP), this.castleBossStartRoom);
        this.roomMapping.put(new RoomIdDirectionKey(14, DOWN), new Room(GARDEN_TRADER)
                .setUp(new Room())
                .setId(17)
                .setRoot("gardenTrader.fxml"));

        //This represents room 15 on the diagram
        this.roomMapping.put(new RoomIdDirectionKey(15, UP), this.castleBossStartRoom);
        this.roomMapping.put(new RoomIdDirectionKey(15, DOWN), new Room(GARDEN_TRADER)
                .setUp(new Room())
                .setId(18)
                .setRoot("gardenTrader.fxml"));

        //Room 19 mappings would go here
        this.roomMapping.put(new RoomIdDirectionKey(20, UP), new Room(BOSS)
                .setDown(new Room())
                .setId(21)
                .setRoot("castleBossUp.fxml"));
        this.roomMapping.put(new RoomIdDirectionKey(19, LEFT), new Room(BOSS)
                .setRight(new Room())
                .setId(21)
                .setRoot("castleBossLeft.fxml"));
        this.roomMapping.put(new RoomIdDirectionKey(21, RIGHT), new Room(BOSS)
                .setLeft(new Room())
                .setId(21)
                .setRoot("castleBossRight.fxml"));
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

    public DirectionService getDirectionService() {
        return directionService;
    }

    public RoomDirectionService setDirectionService(DirectionService directionService) {
        this.directionService = directionService;
        return this;
    }
}

class RoomIdDirectionKey {
    private int id;
    private Direction direction;

    public RoomIdDirectionKey(int id, Direction direction) {
        this.id = id;
        this.direction = direction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.direction);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        RoomIdDirectionKey roomIdDirectionKey = (RoomIdDirectionKey) o;
        return this.id == roomIdDirectionKey.id && this.direction == roomIdDirectionKey.direction;
    }
}
