package com.WildWorldSimulator.constants;

import com.WildWorldSimulator.classes.Point;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class MapDirectionTest {

    @Test
    public void toUnitVector() {
        assertEquals(new Point(1, 0), MapDirection.E.toUnitVector());
        assertEquals(new Point(1, 1), MapDirection.NE.toUnitVector());
        assertEquals(new Point(0, 1), MapDirection.N.toUnitVector());
        assertEquals(new Point(-1, 0), MapDirection.W.toUnitVector());
        assertEquals(new Point(-1, 1), MapDirection.NW.toUnitVector());
        assertEquals(new Point(-1, -1), MapDirection.SW.toUnitVector());
        assertEquals(new Point(0, -1), MapDirection.S.toUnitVector());
        assertEquals(new Point(1, -1), MapDirection.SE.toUnitVector());
    }

    @Test
    public void getNextDirection() {
        MapDirection S = MapDirection.S;
        assertEquals(MapDirection.S, S.getNextDirection(0));
        assertEquals(MapDirection.SW, S.getNextDirection(1));
        assertEquals(MapDirection.W, S.getNextDirection(2));
        assertEquals(MapDirection.NW, S.getNextDirection(3));
        assertEquals(MapDirection.N, S.getNextDirection(4));
        assertEquals(MapDirection.NE, S.getNextDirection(5));
        assertEquals(MapDirection.E, S.getNextDirection(6));
        assertEquals(MapDirection.SE, S.getNextDirection(7));
        assertEquals(MapDirection.S, S.getNextDirection(8));
    }
}