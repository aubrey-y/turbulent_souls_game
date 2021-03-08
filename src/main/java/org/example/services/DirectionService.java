package org.example.services;

import org.example.enums.Direction;

import static org.example.enums.Direction.DOWN;
import static org.example.enums.Direction.LEFT;
import static org.example.enums.Direction.RIGHT;
import static org.example.enums.Direction.UP;

public class DirectionService {

    public DirectionService() {

    }

    public Direction getOppositeDirection(Direction direction) {
        switch (direction) {
            case UP:
                return DOWN;
            case DOWN:
                return UP;
            case RIGHT:
                return LEFT;
            case LEFT:
                return RIGHT;
        }
        return null;
    }
}
