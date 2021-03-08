package org.example.services;

import org.example.dto.Room;
import org.example.enums.Direction;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.example.enums.Direction.DOWN;
import static org.example.enums.Direction.LEFT;
import static org.example.enums.Direction.RIGHT;
import static org.example.enums.Direction.UP;
import static org.example.enums.RoomType.FOREST2;
import static org.example.enums.RoomType.FOREST_TRADER;

public class RoomDirectionService {

    private DirectionService directionService;

    private final Map<RoomIdDirectionKey, Room> roomMapping = new HashMap<>();

    public RoomDirectionService(DirectionService directionService) {
        this.directionService = directionService;
        this.roomMapping.put(new RoomIdDirectionKey(0, LEFT), new Room(FOREST2)
                .setUp(new Room())
                .setRight(new Room())
                .setDown(new Room())
                .setId(1)
                .setRoot("forestLeft"));
        this.roomMapping.put(new RoomIdDirectionKey(0, UP), new Room(FOREST2)
                .setUp(new Room())
                .setDown(new Room())
                .setId(2)
                .setRoot("forestUp"));
        this.roomMapping.put(new RoomIdDirectionKey(0, RIGHT), new Room(FOREST2)
                .setUp(new Room())
                .setLeft(new Room())
                .setDown(new Room())
                .setId(3)
                .setRoot("forestRight"));
        this.roomMapping.put(new RoomIdDirectionKey(0, DOWN), new Room(FOREST_TRADER)
                .setUp(new Room())
                .setId(4)
                .setRoot("forestTrader"));
    }

    public Room getRoomForRoomAndDirection(Room room, Direction direction) {
        Room target = this.roomMapping.get(new RoomIdDirectionKey(room.getId(), direction));
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
        if(o == this) {
            return true;
        }
        RoomIdDirectionKey roomIdDirectionKey = (RoomIdDirectionKey) o;
        return this.id == roomIdDirectionKey.id && this.direction == roomIdDirectionKey.direction;
    }
}
