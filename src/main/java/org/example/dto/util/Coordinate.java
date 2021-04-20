package org.example.dto.util;

import java.util.Objects;

public class Coordinate {

    private int x;

    private int y;

    public Coordinate() {

    }

    public int getX() {
        return x;
    }

    public Coordinate setX(int x) {
        this.x = x;
        return this;
    }

    public int getY() {
        return y;
    }

    public Coordinate setY(int y) {
        this.y = y;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Coordinate)) {
            return false;
        }
        Coordinate coordinate = (Coordinate) o;
        return this.x == coordinate.x && this.y == coordinate.y;
    }
}
