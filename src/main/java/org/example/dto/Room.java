package org.example.dto;

import org.example.enums.RoomType;

public class Room {

    private Room up;
    private Room right;
    private Room down;
    private Room left;

    private RoomType roomType;

    private String root;

    private int id;

    private Class<?> controllerClass;

    public Room() {

    }

    public Room(RoomType roomType) {
        this.roomType = roomType;
    }

    public Room getUp() {
        return up;
    }

    public Room setUp(Room up) {
        this.up = up;
        return this;
    }

    public Room getRight() {
        return right;
    }

    public Room setRight(Room right) {
        this.right = right;
        return this;
    }

    public Room getDown() {
        return down;
    }

    public Room setDown(Room down) {
        this.down = down;
        return this;
    }

    public Room getLeft() {
        return left;
    }

    public Room setLeft(Room left) {
        this.left = left;
        return this;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public Room setRoomType(RoomType roomType) {
        this.roomType = roomType;
        return this;
    }

    public String getRoot() {
        return root;
    }

    public Room setRoot(String root) {
        this.root = root;
        return this;
    }

    public int getId() {
        return id;
    }

    public Room setId(int id) {
        this.id = id;
        return this;
    }

    public Class<?> getControllerClass() {
        return controllerClass;
    }

    public Room setControllerClass(Class<?> controllerClass) {
        this.controllerClass = controllerClass;
        return this;
    }
}
