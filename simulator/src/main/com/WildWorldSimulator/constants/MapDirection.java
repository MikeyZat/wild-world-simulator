package com.WildWorldSimulator.constants;

import com.WildWorldSimulator.classes.Point;

import java.util.Random;

public enum MapDirection {
    N,
    NE,
    NW,
    E,
    S,
    SE,
    SW,
    W;


    private static MapDirection[] enumValues = values();

    public MapDirection next(){
        return enumValues[(this.ordinal()+1) % enumValues.length];
    }

    public MapDirection previous(){
        return enumValues[(this.ordinal()-1) % enumValues.length];
    }

    @Override
    public String toString() {
        switch (this) {
            case N: return "N";
            case S: return "S";
            case W: return "W";
            case E: return "E";
            case NE: return "NE";
            case NW: return "NW";
            case SE: return "SE";
            case SW: return "SW";
            default: return "Default";
        }
    }

    public static MapDirection getRandomDirection() {
        Random generator = new Random();
        return values()[generator.nextInt(values().length)];
    }

    public Point toUnitVector() {
        int x = 0;
        int y = 0;
        switch (this) {
            case N: y = 1; break;
            case S: y = -1; break;
            case W: x = -1; break;
            case E: x = 1; break;
            case NE: x = 1; y = 1; break;
            case NW: x = -1; y = 1; break;
            case SE: x = 1; y = -1; break;
            case SW: x = -1; y = -1; break;
        }
        return new Point(x, y);
    }
}
