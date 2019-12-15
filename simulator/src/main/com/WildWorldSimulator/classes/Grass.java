package com.WildWorldSimulator.classes;

import com.WildWorldSimulator.interfaces.IMapObject;

public class Grass implements IMapObject {
    private Point position;

    public Grass(Point position) {
        this.position = position;
    }

    public Point getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "*";
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (!(other instanceof Grass))
            return false;
        Grass that = (Grass) other;
        return (position.equals(that.getPosition()));
    }
}
