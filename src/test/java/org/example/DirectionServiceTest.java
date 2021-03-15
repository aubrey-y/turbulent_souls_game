package org.example;

import org.example.enums.Direction;
import org.example.services.DirectionService;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DirectionServiceTest {
    @Test
    public void directionUp() {
        DirectionService direction = new DirectionService();
        Direction test = direction.getOppositeDirection(Direction.UP);
        assertThat(test, is(Direction.DOWN));
    }

    @Test
    public void directionDown() {
        DirectionService direction = new DirectionService();
        Direction test = direction.getOppositeDirection(Direction.DOWN);
        assertThat(test, is(Direction.UP));
    }

    @Test
    public void directionLeft() {
        DirectionService direction = new DirectionService();
        Direction test = direction.getOppositeDirection(Direction.LEFT);
        assertThat(test, is(Direction.RIGHT));
    }

    @Test
    public void directionRight() {
        DirectionService direction = new DirectionService();
        Direction test = direction.getOppositeDirection(Direction.RIGHT);
        assertThat(test, is(Direction.LEFT));
    }
}
