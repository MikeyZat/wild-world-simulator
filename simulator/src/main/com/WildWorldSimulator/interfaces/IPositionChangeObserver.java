package com.WildWorldSimulator.interfaces;

import com.WildWorldSimulator.classes.Point;

public interface IPositionChangeObserver {

    void positionChanged(Point oldPosition, Point newPosition);
    void animalDied(Point position);
}