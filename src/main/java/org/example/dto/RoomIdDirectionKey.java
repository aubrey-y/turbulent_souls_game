package org.example.dto;

import org.example.enums.Direction;

import java.util.Objects;

public class RoomIdDirectionKey {
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
