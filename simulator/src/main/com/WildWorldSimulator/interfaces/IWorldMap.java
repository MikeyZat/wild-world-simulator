package com.WildWorldSimulator.interfaces;

import com.WildWorldSimulator.classes.*;
import com.WildWorldSimulator.constants.StartingParams;

public interface IWorldMap extends IGrassMap, IAnimalMap, IAnimalObserver {

    boolean isOccupied(Point position);
    Object objectAt(Point position);
    void executeDay();
    StartingParams getStartingParams();
    Point getJungleLowerLeft();
    Point getJungleUpperRight();
}
