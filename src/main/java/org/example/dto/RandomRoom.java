package org.example.dto;

import org.example.enums.RoomType;

import java.util.List;
import java.util.Random;

public class RandomRoom extends Room {

    private List<Room> rooms;

    public RandomRoom(RoomType roomType, List<Room> rooms) {
        super(roomType);
        this.rooms = rooms;
    }

    public Room getRandomRoom() {
        return this.rooms.get(new Random().nextInt(this.rooms.size()));
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public RandomRoom setRooms(List<Room> rooms) {
        this.rooms = rooms;
        return this;
    }
}
